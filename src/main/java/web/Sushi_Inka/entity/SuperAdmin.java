package web.Sushi_Inka.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "superadmin")
@SQLDelete(sql = "UPDATE superadmin SET estado = 0 WHERE id_superadmin = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class SuperAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_superadmin")
	private Integer idSuperAdmin;

	@Column(name = "usuario_superadmin")
	private String usuarioSuperAdmin;

	@Column(name = "contrasena")
	private String contrasena;

	@Column(name = "email")
	private String email;

	@Column(name = "nombre_completo")
	private String nombreCompleto;

	@Column(name = "ultimo_login")
	private String ultimoLogin;

	@Column(name = "estado")
	private Integer estado;

	@Column(name = "rol") // Added rol field
	private String rol;

	public SuperAdmin() {
	}

	public Integer getIdSuperAdmin() {
		return idSuperAdmin;
	}

	public void setIdSuperAdmin(Integer idSuperAdmin) {
		this.idSuperAdmin = idSuperAdmin;
	}

	public String getUsuarioSuperAdmin() {
		return usuarioSuperAdmin;
	}

	public void setUsuarioSuperAdmin(String usuarioSuperAdmin) {
		this.usuarioSuperAdmin = usuarioSuperAdmin;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(String ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "SuperAdmin [idSuperAdmin=" + idSuperAdmin + ", usuarioSuperAdmin=" + usuarioSuperAdmin + ", email="
				+ email + ", nombreCompleto=" + nombreCompleto + ", estado=" + estado + "]";
	}

}

