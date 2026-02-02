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
@Table(name = "usuario")
@SQLDelete(sql = "UPDATE usuario SET estado = 0 WHERE id_usuario = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "nombre_usuario_login")
	private String nombreUsuarioLogin;

	@Column(name = "contrasena")
	private String contrasena;

	@Column(name = "id_rol")
	private Integer rolId;

	@Column(name = "id_sucursal")
	private Integer idSucursal;

	@Column(name = "estado")
	private Integer estado = 1;

	@Column(name = "ultimo_login")
	private String ultimoLogin;

	public Usuarios() {
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombreUsuarioLogin() {
		return nombreUsuarioLogin;
	}

	public void setNombreUsuarioLogin(String nombreUsuarioLogin) {
		this.nombreUsuarioLogin = nombreUsuarioLogin;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(String ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", apellidos=" + apellidos
				+ ", nombreUsuarioLogin=" + nombreUsuarioLogin + ", rolId=" + rolId + ", idSucursal=" + idSucursal
				+ ", estado=" + estado + "]";
	}

}

