package web.Sushi_Inka.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bitacora_accion")
@SQLDelete(sql = "UPDATE bitacora_accion SET estado = 0 WHERE id_bitacora = ?")
@Where(clause = "estado = 1")
public class BitacoraAccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bitacora")
	private Integer idBitacora;

	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "modulo")
	private String modulo;

	@Column(name = "accion")
	private String accion;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "ip_origen")
	private String ipOrigen;

	@Column(name = "fecha_hora")
	private String fechaHora;

	@Column(name = "estado")
	private Integer estado;

	public BitacoraAccion() {
	}

	public Integer getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(Integer idBitacora) {
		this.idBitacora = idBitacora;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIpOrigen() {
		return ipOrigen;
	}

	public void setIpOrigen(String ipOrigen) {
		this.ipOrigen = ipOrigen;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "BitacoraAccion [idBitacora=" + idBitacora + ", idUsuario=" + idUsuario + ", modulo=" + modulo
				+ ", accion=" + accion + ", fechaHora=" + fechaHora + ", estado=" + estado + "]";
	}

}
