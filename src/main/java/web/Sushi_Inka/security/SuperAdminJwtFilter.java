package web.Sushi_Inka.security;

import java.io.IOException;
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
import web.Sushi_Inka.entity.SuperAdmin;
import web.Sushi_Inka.repository.SuperAdminRepository;

/**
 * SuperAdminJwtFilter (V2)
 * Filtro dedicado EXCLUSIVAMENTE para la autenticación de SuperAdmins.
 * Aislado del sistema legacy.
 */
@Component
public class SuperAdminJwtFilter extends GenericFilter {

    @Autowired
    private SuperAdminJwtUtil jwtUtil; // ✨ Usando V2 Util

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @jakarta.annotation.PostConstruct
    public void init() {
        System.out.println("🚀 SUPERADMIN JWT FILTER CARGADO CORRECTAMENTE 🚀");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        // HttpServletResponse response = (HttpServletResponse) res; // No usado
        // actualmente, pero útil si necesitamos enviar error directo
        String path = request.getRequestURI();

        // 🛑 SOLO procesar rutas de SuperAdmin
        if (!path.startsWith("/restful/superadmin")) {
            chain.doFilter(req, res);
            return;
        }

        System.out.println("🎯 SuperAdminFilter INVOCADO - Path: " + path);

        // 🔓 Permitir rutas de autenticación sin token
        if (request.getMethod().equalsIgnoreCase("OPTIONS") ||
                path.startsWith("/restful/superadmin/auth/")) {
            chain.doFilter(req, res);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            // 1. Validar Token
            if (jwtUtil.validarToken(token)) {
                String username = jwtUtil.extraerClienteId(token);
                System.out.println("🔍 V2 Filter: Token valido. Usuario: '" + username + "'");

                // Priority 1: SuperAdmin ONLY
                Optional<SuperAdmin> superAdminOpt = superAdminRepository.findByEmail(username);
                if (superAdminOpt.isPresent()) {
                    System.out.println("✅ SuperAdmin encontrado: " + superAdminOpt.get().getEmail());

                    if (superAdminOpt.get().getEstado() == 1) {
                        String finalRole = "ROLE_" + superAdminOpt.get().getRol();
                        System.out.println("🔐 V2 AUTH EXITOSA. Rol: " + finalRole);

                        var authorities = java.util.Collections.singletonList(
                                new org.springframework.security.core.authority.SimpleGrantedAuthority(finalRole));
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                username, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    } else {
                        System.out.println("❌ SuperAdmin inactivo.");
                    }
                } else {
                    System.out.println("⚠️ Token valido, pero usuario no encontrado en SuperAdmin table.");
                }
            } else {
                System.out.println("❌ Token Invalido o Expirado (Rechazado por JwtUtil)");
            }
        }

        chain.doFilter(req, res);
    }
}
