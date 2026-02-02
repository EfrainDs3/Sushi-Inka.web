package web.Sushi_Inka.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad para endpoints de API
 * 
 * Protege todos los endpoints bajo /restful/**, /api/**, /regional/**
 * Usa autenticación JWT (stateless) con dos filtros:
 * 1. InternalAuthFilter - Para usuarios del sistema (tabla usuario)
 * 2. ExternalAuthFilter - Para usuarios externos (tabla registros)
 */
@Configuration
@Order(1) // Se procesa primero (más específico)
public class ApiSecurityConfig {

        @Autowired
        private InternalAuthFilter internalAuthFilter;

        @Autowired
        private ExternalAuthFilter externalAuthFilter;

        /**
         * Configuración de seguridad para API REST
         * 
         * - CSRF deshabilitado (API stateless)
         * - CORS habilitado
         * - Sesiones STATELESS (no se crean sesiones en servidor)
         * - Dos filtros de autenticación aplicados en orden:
         * 1. InternalAuthFilter (usuarios del sistema)
         * 2. ExternalAuthFilter (usuarios externos)
         */
        @Bean
        public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
                http
                                // Aplicar solo a rutas de API
                                .securityMatcher("/restful/**", "/api/**", "/regional/**", "/perfiles/**",
                                                "/modulos/**", "/accesos/**")

                                // Deshabilitar CSRF (no necesario para APIs REST stateless)
                                .csrf(csrf -> csrf.disable())

                                // Habilitar CORS
                                .cors(Customizer.withDefaults())

                                // Configurar sesiones como STATELESS (importante para JWT)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                // Configurar autorización de endpoints
                                .authorizeHttpRequests(auth -> auth
                                                // Endpoints públicos (definidos en PublicSecurityConfig)
                                                .requestMatchers(PublicSecurityConfig.PUBLIC_ENDPOINTS).permitAll()

                                                // Todos los demás endpoints de API requieren autenticación
                                                .anyRequest().authenticated())

                                // Agregar filtros de autenticación en orden
                                // 1. InternalAuthFilter - Intenta autenticar como usuario interno
                                .addFilterBefore(internalAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                // 2. ExternalAuthFilter - Si falla, intenta autenticar como usuario externo
                                .addFilterAfter(externalAuthFilter, InternalAuthFilter.class);

                return http.build();
        }
}

