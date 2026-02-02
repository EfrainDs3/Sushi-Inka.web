package web.Sushi_Inka.security;

import org.springframework.context.annotation.Configuration;

/**
 * Configuración centralizada de endpoints públicos
 * 
 * Define qué endpoints NO requieren autenticación
 */
@Configuration
public class PublicSecurityConfig {

    /**
     * Endpoints públicos que NO requieren autenticación
     */
    public static final String[] PUBLIC_ENDPOINTS = {
            // Login y registro
            "/restful/usuarios/login",
            "/restful/token",
            "/restful/registros",
            "/restful/superadmin/login",
            "/restful/superadmin/auth/initiate-login",

            // Endpoints públicos de API (para testing)
            "/api/categorias/**",
            "/api/platos/**",

            // Documentación API (Swagger - si se implementa en el futuro)
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**",

            // Health check (Actuator - si se implementa en el futuro)
            "/actuator/health",
            "/actuator/info"
    };

    /**
     * Verifica si una ruta es pública
     * 
     * @param path Ruta a verificar
     * @return true si es pública, false si requiere autenticación
     */
    public static boolean isPublicEndpoint(String path) {
        if (path == null) {
            return false;
        }

        for (String endpoint : PUBLIC_ENDPOINTS) {
            // Convertir patrón Ant a regex
            String regex = endpoint
                    .replace("**", ".*")
                    .replace("*", "[^/]*");

            if (path.matches(regex)) {
                return true;
            }
        }

        return false;
    }
}


