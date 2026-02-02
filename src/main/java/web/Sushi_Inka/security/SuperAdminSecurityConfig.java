package web.Sushi_Inka.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

/**
 * SuperAdminSecurityConfig (V2)
 *
 * Configuración de seguridad ESTRICTAMENTE para el módulo de SuperAdmin.
 * Tiene prioridad (@Order(1)) sobre la configuración general.
 */
@Configuration
public class SuperAdminSecurityConfig {

    @Bean
    @Order(1) // ⚠️ CRÍTICO: Debe ejecutarse ANTES que la config general
    public SecurityFilterChain superAdminFilterChain(HttpSecurity http, SuperAdminJwtFilter superAdminJwtFilter)
            throws Exception {

        System.out.println("🛡️ Cargando Chain de Seguridad SuperAdmin (V2) 🛡️");

        http
                .securityMatcher("/restful/superadmin/**") // Solo aplica a estas rutas
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas de SuperAdmin
                        .requestMatchers(
                                "/restful/superadmin/auth/initiate-login",
                                "/restful/superadmin/auth/login")
                        .permitAll()

                        // Todo lo demás de SuperAdmin requiere autenticación
                        .anyRequest().authenticated())
                // Insertamos NUESTRO filtro aislado antes del Anonymous
                .addFilterBefore(superAdminJwtFilter, AnonymousAuthenticationFilter.class);

        return http.build();
    }
}

