package web.Sushi_Inka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // --- 1. DATOS DEL SERVIDOR (Sacados de tu captura) ---
        // Tu servidor de salida exacto:
        mailSender.setHost("comidas.spring.informaticapp.com");
        mailSender.setPort(465); // Puerto seguro SSL

        // --- 2. TUS CREDENCIALES CORPORATIVAS ---
        // Tu usuario exacto:
        mailSender.setUsername("admin@comidas.spring.informaticapp.com");

        // 👇 IMPORTANTE: Escribe aquí la contraseña que le pusiste a este correo en
        // cPanel
        mailSender.setPassword("Darkoss19.-");

        // --- 3. CONFIGURACIÓN DE SEGURIDAD ---
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", "true");

        // Para puerto 465 (SSL), StartTLS se apaga y SSL se prende:
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.starttls.required", "false");
        props.put("mail.smtp.ssl.enable", "true");

        // --- 4. CONFIANZA EN CERTIFICADOS (Para evitar errores con el servidor propio)
        // ---
        props.put("mail.smtps.ssl.trust", "*");
        props.put("mail.smtp.ssl.trust", "*");

        // Dejamos el debug activado para confirmar el envío
        props.put("mail.debug", "true");

        return mailSender;
    }
}

