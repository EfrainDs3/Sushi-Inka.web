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
@Table(name = "insumos")
@SQLDelete(sql = "UPDATE insumos SET estado = 0 WHERE id_insumo = ?")
@Where(clause = "estado = 1")
public class Insumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_insumo")
	private Integer idInsumo;

	@Column(name = "id_sucursal")
	private Integer idSucursal;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "stock_actual")
	private Double stockActual;

	@Column(name = "stock_minimo")
	private Double stockMinimo;

	@Column(name = "unidad_medida")
	private String unidadMedida;

	@Column(name = "precio_unitario")
	private Double precioUnitario;

	@Column(name = "fecha_vencimiento")
	private String fechaVencimiento;

	@Column(name = "estado")
	private Integer estado = 1;

	public Insumo() {
	}

	public Integer getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Integer idInsumo) {
		this.idInsumo = idInsumo;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getStockActual() {
		return stockActual;
	}

	public void setStockActual(Double stockActual) {
		this.stockActual = stockActual;
	}

	public Double getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(Double stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Insumo [idInsumo=" + idInsumo + ", nombre=" + nombre + ", stockActual=" + stockActual + ", estado="
				+ estado + "]";
	}

}
