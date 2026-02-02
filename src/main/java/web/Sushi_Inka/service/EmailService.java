package web.Sushi_Inka.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void enviarTokenSuperAdmin(String email, String token, String nombreUsuario) {
		// Implementar lógica para enviar email
		System.out.println("Email enviado a: " + email);
		System.out.println("Token: " + token);
		System.out.println("Usuario: " + nombreUsuario);
	}

}

