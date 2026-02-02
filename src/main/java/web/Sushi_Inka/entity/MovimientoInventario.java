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
@Table(name = "movimientos_inventario")
@SQLDelete(sql = "UPDATE movimientos_inventario SET estado = 0 WHERE id_movimiento_inventario = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class MovimientoInventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movimiento_inventario")
	private Integer idMovimientoInventario;

	@Column(name = "id_insumo")
	private Integer idInsumo;

	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;

	@Column(name = "cantidad")
	private Double cantidad;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "fecha_hora")
	private String fechaHora;

	@Column(name = "estado")
	private Integer estado;

	public MovimientoInventario() {
	}

	public Integer getIdMovimientoInventario() {
		return idMovimientoInventario;
	}

	public void setIdMovimientoInventario(Integer idMovimientoInventario) {
		this.idMovimientoInventario = idMovimientoInventario;
	}

	public Integer getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Integer idInsumo) {
		this.idInsumo = idInsumo;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
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
		return "MovimientoInventario [idMovimientoInventario=" + idMovimientoInventario + ", idInsumo=" + idInsumo
				+ ", tipoMovimiento=" + tipoMovimiento + ", cantidad=" + cantidad + ", estado=" + estado + "]";
	}

}

