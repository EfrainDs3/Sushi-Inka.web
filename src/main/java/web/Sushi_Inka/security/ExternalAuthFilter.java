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
import web.Sushi_Inka.entity.Registros;
import web.Sushi_Inka.repository.RegistrosRepository;

/**
 * Filtro de autenticación para usuarios EXTERNOS
 * 
 * Valida tokens de acceso almacenados en la tabla 'registros'
 * Estos son usuarios externos o clientes del sistema
 */
@Component
@Order(2) // Se ejecuta después de InternalAuthFilter
public class ExternalAuthFilter extends GenericFilter {

    @Autowired
    private RegistrosRepository registrosRepository;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();

        // Skip para endpoints públicos
        if (PublicSecurityConfig.isPublicEndpoint(requestURI)) {
            chain.doFilter(req, res);
            return;
        }

        // 🛑 SKIP SuperAdmin paths - deja que JwtFilter los maneje
        if (requestURI.startsWith("/restful/superadmin")) {
            chain.doFilter(req, res);
            return;
        }

        // Si ya está autenticado (por InternalAuthFilter), continuar
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            chain.doFilter(req, res);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                // Buscar token en tabla de registros (usuarios externos)
                Optional<Registros> match = registrosRepository.findAll()
                        .stream()
                        .filter(r -> token.equals(r.getAccess_token()))
                        .findFirst();

                if (match.isPresent()) {
                    Registros registro = match.get();
                    String clienteId = registro.getEmail();

                    // Autenticar usuario externo
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            clienteId,
                            null,
                            Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    // Log exitoso (opcional)
                    System.out.println("✅ Usuario externo autenticado: " + clienteId);
                }
            } catch (Exception e) {
                // Log error pero continuar
                System.err.println("❌ Error en ExternalAuthFilter: " + e.getMessage());
            }
        }

        chain.doFilter(req, res);
    }
}

