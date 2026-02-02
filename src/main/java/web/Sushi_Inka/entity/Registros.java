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
@Table(name = "registros")
@SQLDelete(sql = "UPDATE registros SET estado = 0 WHERE id_registro = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class Registros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro")
	private Integer idRegistro;

	@Column(name = "email")
	private String email;

	@Column(name = "token")
	private String token;

	@Column(name = "tipo_registro")
	private String tipoRegistro;

	@Column(name = "fecha_solicitud")
	private String fechaSolicitud;

	@Column(name = "fecha_expiracion")
	private String fechaExpiracion;

	@Column(name = "estado")
	private Integer estado;

	public Registros() {
	}

	public Integer getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Registros [idRegistro=" + idRegistro + ", email=" + email + ", tipoRegistro=" + tipoRegistro
				+ ", fechaSolicitud=" + fechaSolicitud + ", estado=" + estado + "]";
	}

}
