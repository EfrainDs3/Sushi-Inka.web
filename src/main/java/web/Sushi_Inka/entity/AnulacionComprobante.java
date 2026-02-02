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
@Table(name = "anulacion_comprobante")
@SQLDelete(sql = "UPDATE anulacion_comprobante SET estado = 0 WHERE id_anulacion = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class AnulacionComprobante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_anulacion")
	private Integer idAnulacion;

	@Column(name = "id_venta")
	private Integer idVenta;

	@Column(name = "motivo_anulacion")
	private String motivoAnulacion;

	@Column(name = "usuario_anula")
	private Integer usuarioAnula;

	@Column(name = "fecha_anulacion")
	private String fechaAnulacion;

	@Column(name = "estado")
	private Integer estado;

	public AnulacionComprobante() {
	}

	public Integer getIdAnulacion() {
		return idAnulacion;
	}

	public void setIdAnulacion(Integer idAnulacion) {
		this.idAnulacion = idAnulacion;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public Integer getUsuarioAnula() {
		return usuarioAnula;
	}

	public void setUsuarioAnula(Integer usuarioAnula) {
		this.usuarioAnula = usuarioAnula;
	}

	public String getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "AnulacionComprobante [idAnulacion=" + idAnulacion + ", idVenta=" + idVenta + ", motivoAnulacion="
				+ motivoAnulacion + ", fechaAnulacion=" + fechaAnulacion + ", estado=" + estado + "]";
	}

}

