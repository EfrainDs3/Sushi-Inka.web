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
@Table(name = "movimientos_caja")
@SQLDelete(sql = "UPDATE movimientos_caja SET estado = 0 WHERE id_movimiento_caja = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class MovimientoCaja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movimiento_caja")
	private Integer idMovimientoCaja;

	@Column(name = "id_sesion_caja")
	private Integer idSesionCaja;

	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;

	@Column(name = "monto")
	private Double monto;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "fecha_hora")
	private String fechaHora;

	@Column(name = "estado")
	private Integer estado;

	public MovimientoCaja() {
	}

	public Integer getIdMovimientoCaja() {
		return idMovimientoCaja;
	}

	public void setIdMovimientoCaja(Integer idMovimientoCaja) {
		this.idMovimientoCaja = idMovimientoCaja;
	}

	public Integer getIdSesionCaja() {
		return idSesionCaja;
	}

	public void setIdSesionCaja(Integer idSesionCaja) {
		this.idSesionCaja = idSesionCaja;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		return "MovimientoCaja [idMovimientoCaja=" + idMovimientoCaja + ", idSesionCaja=" + idSesionCaja
				+ ", tipoMovimiento=" + tipoMovimiento + ", monto=" + monto + ", estado=" + estado + "]";
	}

}

