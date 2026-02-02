package web.Sushi_Inka.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
import web.Sushi_Inka.entity.Usuarios;
import web.Sushi_Inka.repository.UsuariosRepository;

/**
 * Filtro de autenticación para usuarios INTERNOS del sistema
 * 
 * Valida tokens JWT generados para usuarios de la tabla 'usuario'
 * Estos son usuarios del sistema (administradores, cajeros, etc.)
 */
@Component
@Order(1) // Se ejecuta primero
public class InternalAuthFilter extends GenericFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String requestURI = request.getRequestURI();

        // Skip para endpoints públicos
        if (PublicSecurityConfig.isPublicEndpoint(requestURI)) {
            chain.doFilter(req, res);
            return;
        }

        // 🛑 SKIP SuperAdmin paths - deja que JwtFilter los maneje
        System.out.println("🔍 InternalAuthFilter - URI: " + requestURI);
        if (requestURI.startsWith("/restful/superadmin")) {
            System.out.println("⏩ InternalAuthFilter SKIPPING SuperAdmin path");
            chain.doFilter(req, res);
            return;
        }

        // Si ya está autenticado (por otro filtro), continuar
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            chain.doFilter(req, res);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                // Validar token JWT (solo para usuarios internos)
                if (jwtUtil.validarToken(token)) {
                    String username = jwtUtil.extraerClienteId(token);
                    Optional<Usuarios> usuarioOpt = usuarioRepository.findByNombreUsuarioLogin(username);

                    if (usuarioOpt.isPresent()) {
                        Usuarios usuario = usuarioOpt.get();

                        // Validar que el usuario esté activo
                        if (usuario.getEstado() == null || usuario.getEstado() != 1) {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Usuario inactivo\",\"type\":\"INACTIVE_USER\"}");
                            return;
                        }

                        // Autenticar usuario
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.emptyList());
                        SecurityContextHolder.getContext().setAuthentication(auth);

                        // Log exitoso (opcional)
                        System.out.println("✅ Usuario interno autenticado: " + username);
                    }
                }
            } catch (Exception e) {
                // Log error pero continuar (dejar que Spring Security maneje)
                System.err.println("❌ Error en InternalAuthFilter: " + e.getMessage());
            }
        }

        chain.doFilter(req, res);
    }
}
