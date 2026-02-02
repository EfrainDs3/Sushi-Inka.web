package web.Sushi_Inka.entity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.annotations.SQLDelete;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "registros")

@SQLDelete(sql = "UPDATE registros SET estado = 0 WHERE id_registro = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")

public class Registros {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_registro;
	private String nombres;
	private String apellidos;
	private String email;
	private String id_usuario;
	private String llave_secreta;
	private String access_token;
	public static final String DEFAULT_ACCESS_TOKEN = "default_access_token";
	private Integer estado = 1;

	public Integer getId_registro() {
		return id_registro;
	}

	public void setId_registro(Integer id_registro) {
		this.id_registro = id_registro;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		String datos = nombres + apellidos + email;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(datos.getBytes());
			byte[] digest = md.digest();
			String result = new BigInteger(1, digest).toString(16).toLowerCase();
			id_usuario = result;
			this.id_usuario = id_usuario;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			this.id_usuario = id_usuario;
		}
	}

	public String getLlave_secreta() {
		return llave_secreta;
	}

	public void setLlave_secreta(String llave_secreta) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.llave_secreta = encoder.encode(llave_secreta);
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		if (access_token == null || access_token.trim().isEmpty()) {
			this.access_token = DEFAULT_ACCESS_TOKEN;
		} else {
			this.access_token = access_token;
		}
	}

	@Override
	public String toString() {
		return "Registros [id_registro=" + id_registro + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", email=" + email + ", id_usuario=" + id_usuario + ", llave_secreta=" + llave_secreta
				+ ", access_token=" + access_token + ", estado=" + estado + "]";
	}


}

