package web.Sushi_Inka.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

        /**
         * Configuración de la cadena de filtros de seguridad
         * 
         * Define qué endpoints son públicos y cuáles requieren autenticación
         * Integra el JwtFilter para validar tokens en cada petición
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
                http.securityMatcher("/restful/**")
                                .csrf(csrf -> csrf.disable())
                                .cors(Customizer.withDefaults())
                                .authorizeHttpRequests(auth -> auth
                                                // Endpoints públicos de autenticación
                                                .requestMatchers(
                                                                "/restful/token",
                                                                "/restful/registros",
                                                                "/restful/usuarios/login",
                                                                "/restful/superadmin/auth/initiate-login",
                                                                "/restful/superadmin/auth/login")
                                                .permitAll()
                                                // Todos los demás requieren autenticación
                                                .anyRequest().authenticated())
                                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        /**
         * Bean para encriptación de contraseñas usando BCrypt
         * 
         * BCrypt es más seguro que SHA-256 porque:
         * - Incluye salt automático
         * - Es más lento (dificulta ataques de fuerza bruta)
         * - Es adaptativo (puede aumentar complejidad en el futuro)
         */
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        /**
         * Configuración CORS global
         * 
         * Permite peticiones desde cualquier origen durante desarrollo
         * En producción, deberías restringir los orígenes permitidos
         */
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();

                // Orígenes permitidos - dominios específicos para producción
                configuration.setAllowedOriginPatterns(Arrays.asList(
                                "http://localhost:*",
                                "https://localhost:*",
                                "https://*.vercel.app",
                                "https://*.informaticapp.com",
                                "https://informaticapp.com"));

                // Métodos HTTP permitidos
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

                // Headers permitidos
                configuration.setAllowedHeaders(Arrays.asList("*"));

                // Permitir credenciales
                configuration.setAllowCredentials(true);

                // Aplicar configuración a todas las rutas
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;
        }
}

