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
@Table(name = "pago_suscripcion")
@SQLDelete(sql = "UPDATE pago_suscripcion SET estado = 0 WHERE id_pago_suscripcion = ?")
@Where(clause = "estado = 1")
public class PagoSuscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pago_suscripcion")
	private Integer idPagoSuscripcion;

	@Column(name = "id_restaurante")
	private Integer idRestaurante;

	@Column(name = "monto_pago")
	private Double montoPago;

	@Column(name = "tipo_suscripcion")
	private String tipoSuscripcion;

	@Column(name = "fecha_pago")
	private String fechaPago;

	@Column(name = "fecha_vencimiento")
	private String fechaVencimiento;

	@Column(name = "numero_referencia")
	private String numeroReferencia;

	@Column(name = "estado")
	private Integer estado;

	public PagoSuscripcion() {
	}

	public Integer getIdPagoSuscripcion() {
		return idPagoSuscripcion;
	}

	public void setIdPagoSuscripcion(Integer idPagoSuscripcion) {
		this.idPagoSuscripcion = idPagoSuscripcion;
	}

	public Integer getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Integer idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public Double getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(Double montoPago) {
		this.montoPago = montoPago;
	}

	public String getTipoSuscripcion() {
		return tipoSuscripcion;
	}

	public void setTipoSuscripcion(String tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "PagoSuscripcion [idPagoSuscripcion=" + idPagoSuscripcion + ", idRestaurante=" + idRestaurante
				+ ", montoPago=" + montoPago + ", tipoSuscripcion=" + tipoSuscripcion + ", fechaPago=" + fechaPago
				+ ", estado=" + estado + "]";
	}

}
