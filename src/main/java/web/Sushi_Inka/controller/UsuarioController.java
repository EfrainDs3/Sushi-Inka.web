package web.Sushi_Inka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.Sushi_Inka.entity.Usuarios;
import web.Sushi_Inka.security.JwtUtil;
import web.Sushi_Inka.service.IUsuarioService;
import web.Sushi_Inka.service.EmailService;

@RestController
@RequestMapping("/restful")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private web.Sushi_Inka.service.IPerfilService perfilService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private web.Sushi_Inka.repository.SucursalesRepository repoSucursal;

    @GetMapping("/usuarios")
    public List<Usuarios> listar() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/usuarios/sucursal/{idSucursal}")
    public ResponseEntity<?> obtenerPorSucursal(@PathVariable Integer idSucursal) {
        try {
            List<Usuarios> usuarios = usuarioService.getUsuariosBySucursal(idSucursal);
            return ResponseEntity.ok(usuarios);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener usuarios por sucursal");
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        Optional<Usuarios> opt = usuarioService.getUsuarioById(id);
        return opt.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado"));
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> crear(@RequestBody Usuarios usuario) {
        try {
            Usuarios creado = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Usuarios usuario) {
        Optional<Usuarios> actualizado = usuarioService.updateUsuario(id, usuario);
        return actualizado.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado"));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok("Usuario eliminado");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("nombreUsuarioLogin");
        String plainPassword = loginRequest.get("contrasena");

        System.out.println("Intento de login para: " + username);
        System.out.println("Contraseña recibida (texto plano): " + plainPassword);

        Optional<Usuarios> usuarioOpt = usuarioService.getUsuarioByLogin(username);

        if (usuarioOpt.isPresent()) {
            Usuarios usuario = usuarioOpt.get();
            System.out.println("Usuario encontrado en BD: " + usuario.getNombreUsuarioLogin());

            // Verify password using BCrypt
            System.out.println("Contraseña recibida: " + plainPassword);
            System.out.println("Hash en BD: " + usuario.getContrasena());

            if (passwordEncoder.matches(plainPassword, usuario.getContrasena())) {
                System.out.println("¡Contraseña correcta!");
                String token = jwtUtil.generarToken(usuario.getNombreUsuarioLogin());

                // Obtener el nombre del perfil real del usuario
                String nombrePerfil = "Sin perfil";
                if (usuario.getRolId() != null) {
                    nombrePerfil = Optional.ofNullable(perfilService.obtenerPorId(usuario.getRolId()))
                            .map(perfil -> perfil.getNombrePerfil())
                            .orElse("Sin perfil");
                }

                // Obtener idRestaurante desde la sucursal
                Integer idRestaurante = null;
                Integer idSucursal = usuario.getIdSucursal();
                if (idSucursal != null && idSucursal > 0) {
                    idRestaurante = repoSucursal.findById(idSucursal)
                            .map(s -> s.getIdRestaurante())
                            .orElse(null);
                }

                // Return token and user info
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("idUsuario", usuario.getIdUsuario());
                response.put("nombreUsuario", usuario.getNombreUsuario());
                response.put("apellidos", usuario.getApellidos());
                response.put("nombreUsuarioLogin", usuario.getNombreUsuarioLogin());
                response.put("idPerfil", usuario.getRolId());
                response.put("nombrePerfil", nombrePerfil);
                response.put("idSucursal", usuario.getIdSucursal()); // Multi-tenant: ID de la sucursal
                response.put("idRestaurante", idRestaurante); // ID del restaurante (dueño de la sucursal)

                // Si es SuperAdmin (rolId >= 5), enviar token por email
                if (usuario.getRolId() != null && usuario.getRolId() >= 5) {
                    try {
                        // Usar el email configurado en application.properties como destinatario
                        // En producción, deberías tener un campo email en la tabla usuario
                        String emailDestinatario = "anllyriva14@gmail.com"; // Email del SuperAdmin
                        emailService.enviarTokenSuperAdmin(emailDestinatario, token, usuario.getNombreUsuario());
                        response.put("tokenEnviadoPorEmail", true);
                        response.put("emailDestinatario", emailDestinatario);
                        System.out.println("✅ Token enviado por email a: " + emailDestinatario);
                    } catch (Exception e) {
                        System.err.println("❌ Error al enviar email: " + e.getMessage());
                        response.put("tokenEnviadoPorEmail", false);
                        response.put("errorEmail",
                                "No se pudo enviar el email, pero puedes usar el token directamente");
                    }
                } else {
                    response.put("tokenEnviadoPorEmail", false);
                }

                return ResponseEntity.ok(response);
            } else {
                System.out.println("¡Contraseña incorrecta!");
            }
        } else {
            System.out.println("Usuario no encontrado en BD");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @PostMapping("/usuarios/import")
    public ResponseEntity<?> importarUsuarios(
            @RequestParam("file") org.springframework.web.multipart.MultipartFile file) {
        try {
            // Validar que el archivo sea Excel
            String filename = file.getOriginalFilename();
            if (filename == null || (!filename.endsWith(".xlsx") && !filename.endsWith(".xls"))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El archivo debe ser formato Excel (.xlsx o .xls)");
            }

            // Leer el archivo Excel
            org.apache.poi.ss.usermodel.Workbook workbook;
            if (filename.endsWith(".xlsx")) {
                workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook(file.getInputStream());
            } else {
                workbook = new org.apache.poi.hssf.usermodel.HSSFWorkbook(file.getInputStream());
            }

            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

            List<Map<String, Object>> resultados = new java.util.ArrayList<>();
            int exitosos = 0;
            int fallidos = 0;

            // Contador para generar nombres de usuario únicos
            Map<String, Integer> contadorNombres = new HashMap<>();

            // Saltar la primera fila (encabezados) y procesar desde la fila 1
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                try {
                    // Leer datos del Excel: ID, Nombre, Email, Rol, Estado, Teléfono
                    String idStr = getCellValueAsString(row.getCell(0)); // Columna A: ID (ignorar)
                    String nombreCompleto = getCellValueAsString(row.getCell(1)); // Columna B: Nombre completo
                    String email = getCellValueAsString(row.getCell(2)); // Columna C: Email
                    String rolTexto = getCellValueAsString(row.getCell(3)); // Columna D: Rol (texto)
                    String estadoTexto = getCellValueAsString(row.getCell(4)); // Columna E: Estado
                    String telefono = getCellValueAsString(row.getCell(5)); // Columna F: Teléfono

                    // Validar datos requeridos
                    if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
                        fallidos++;
                        Map<String, Object> error = new HashMap<>();
                        error.put("fila", i + 1);
                        error.put("error", "Nombre completo es requerido");
                        resultados.add(error);
                        continue;
                    }

                    // Separar nombre completo en nombre y apellidos
                    String[] partesNombre = nombreCompleto.trim().split("\\s+", 2);
                    String nombreUsuario = partesNombre[0];
                    String apellidos = partesNombre.length > 1 ? partesNombre[1] : "";

                    // Mapear rol de texto a ID (ajusta según tus roles)
                    Integer rolId = mapearRolTextoAId(rolTexto);

                    // Auto-generar nombre de usuario (login)
                    String baseLogin = nombreUsuario.trim().replaceAll("\\s+", "");
                    int contador = contadorNombres.getOrDefault(baseLogin, 0) + 1;
                    contadorNombres.put(baseLogin, contador);
                    String nombreUsuarioLogin = baseLogin + contador;

                    // Verificar que no exista en la base de datos
                    while (usuarioService.getUsuarioByLogin(nombreUsuarioLogin).isPresent()) {
                        contador++;
                        contadorNombres.put(baseLogin, contador);
                        nombreUsuarioLogin = baseLogin + contador;
                    }

                    // Auto-generar contraseña temporal
                    String contrasenaTemporal = "Temporal123";
                    String contrasenaHash = passwordEncoder.encode(contrasenaTemporal);

                    // Crear usuario
                    Usuarios nuevoUsuario = new Usuarios();
                    nuevoUsuario.setNombreUsuario(nombreUsuario.trim());
                    nuevoUsuario.setApellidos(apellidos.trim());
                    nuevoUsuario.setNombreUsuarioLogin(nombreUsuarioLogin);
                    nuevoUsuario.setContrasena(contrasenaHash);
                    nuevoUsuario.setRolId(rolId);
                    nuevoUsuario.setIdSucursal(0); // Por defecto sin sucursal específica
                    nuevoUsuario.setEstado(1); // Activo

                    usuarioService.registrarUsuario(nuevoUsuario);

                    exitosos++;
                    Map<String, Object> exito = new HashMap<>();
                    exito.put("fila", i + 1);
                    exito.put("nombre", nombreCompleto);
                    exito.put("usuario", nombreUsuarioLogin);
                    exito.put("contrasena", contrasenaTemporal);
                    exito.put("email", email != null ? email : "N/A");
                    resultados.add(exito);

                } catch (Exception e) {
                    fallidos++;
                    Map<String, Object> error = new HashMap<>();
                    error.put("fila", i + 1);
                    error.put("error", e.getMessage());
                    resultados.add(error);
                }
            }

            workbook.close();

            // Preparar respuesta
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exitosos", exitosos);
            respuesta.put("fallidos", fallidos);
            respuesta.put("detalles", resultados);
            respuesta.put("mensaje",
                    "Importación completada: " + exitosos + " usuarios creados, " + fallidos + " errores");

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    // Helper methods para leer celdas de Excel
    private String getCellValueAsString(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null)
            return null;
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return null;
        }
    }

    private Integer getCellValueAsInteger(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null)
            return null;
        try {
            switch (cell.getCellType()) {
                case NUMERIC:
                    return (int) cell.getNumericCellValue();
                case STRING:
                    return Integer.parseInt(cell.getStringCellValue());
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    // Helper method to map role text to role ID
    private Integer mapearRolTextoAId(String rolTexto) {
        if (rolTexto == null)
            return null;

        String rolLower = rolTexto.trim().toLowerCase();
        switch (rolLower) {
            case "admin":
            case "administrador":
                return 5; // Ajusta según tu base de datos
            case "manager":
            case "gerente":
                return 3;
            case "user":
            case "usuario":
                return 2;
            default:
                return null; // Rol no válido
        }
    }

}
