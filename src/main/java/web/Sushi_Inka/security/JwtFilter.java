package web.Sushi_Inka.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.Sushi_Inka.entity.Registros;
import web.Sushi_Inka.entity.SuperAdmin;
import web.Sushi_Inka.entity.Usuarios;
import web.Sushi_Inka.repository.RegistrosRepository;
import web.Sushi_Inka.repository.SuperAdminRepository;
import web.Sushi_Inka.repository.UsuariosRepository;

@Component
public class JwtFilter extends GenericFilter {

    @Autowired
    private RegistrosRepository registrosRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getRequestURI();

        System.out.println("🔍 JwtFilter - Path: " + path);

        // 🛑 SKIP SUPERADMIN: Deja que SuperAdminSecurityConfig y SuperAdminJwtFilter
        // se encarguen
        if (path.startsWith("/restful/superadmin")) {
            System.out.println("⏩ JwtFilter SKIPPING SuperAdmin path: " + path);
            chain.doFilter(req, res);
            return;
        }

        // ✅ NO FILTRAR endpoints públicos de autenticación
        if (path.equals("/restful/usuarios/login") ||
                path.equals("/restful/token") ||
                path.equals("/restful/registros")) {

            System.out.println("✅ Endpoint público - permitiendo acceso sin token");
            chain.doFilter(req, res);
            return;
        }

        // 🔐 VALIDAR TOKEN para endpoints protegidos
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            System.out.println("🔑 Token recibido: " + token.substring(0, Math.min(20, token.length())) + "...");

            // 1️⃣ Intentar autenticación Legacy (Registros)
            Optional<Registros> matchRegistro = registrosRepository.findAll()
                    .stream()
                    .filter(r -> token.equals(r.getAccess_token()))
                    .findFirst();

            if (matchRegistro.isPresent()) {
                String clienteId = matchRegistro.get().getEmail();
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(clienteId, null,
                        Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("✅ Autenticado como Usuario Legacy: " + clienteId);
                chain.doFilter(req, res);
                return;
            }

            // 2️⃣ Validar JWT (puede ser Usuario o SuperAdmin)
            if (jwtUtil.validarToken(token)) {
                String email = jwtUtil.extraerClienteId(token);
                System.out.println("📧 Email extraído del JWT: " + email);

                // 2.A - Verificar si es SuperAdmin
                Optional<SuperAdmin> superAdminOpt = superAdminRepository.findByEmail(email);
                if (superAdminOpt.isPresent()) {
                    SuperAdmin superAdmin = superAdminOpt.get();

                    // Verificar que el SuperAdmin esté activo
                    if (superAdmin.getEstado() != null && superAdmin.getEstado() == 1) {
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null,
                                Collections.emptyList());
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        System.out.println("✅ Autenticado como SuperAdmin: " + email);
                        chain.doFilter(req, res);
                        return;
                    } else {
                        System.out.println("❌ SuperAdmin inactivo: " + email);
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Cuenta de SuperAdmin inactiva");
                        return;
                    }
                }

                // 2.B - Verificar si es Usuario normal
                Optional<Usuarios> usuarioOpt = usuarioRepository.findByNombreUsuarioLogin(email);
                if (usuarioOpt.isPresent()) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null,
                            Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    System.out.println("✅ Autenticado como Usuario: " + email);
                    chain.doFilter(req, res);
                    return;
                }

                System.out.println("❌ Email del JWT no encontrado en ninguna tabla");
            } else {
                System.out.println("❌ Token JWT inválido o expirado");
            }
        } else {
            System.out.println("⚠️ No se encontró header Authorization");
        }

        // ❌ Si llegamos aquí, es un endpoint protegido sin autenticación válida
        if (path.startsWith("/restful/superadmin/")) {
            System.out.println("❌ Acceso denegado a endpoint de SuperAdmin");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
            return;
        }

        // Para otros endpoints, continuar sin autenticación (compatibilidad legacy)
        chain.doFilter(req, res);
    }
}
