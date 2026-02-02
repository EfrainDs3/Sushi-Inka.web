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
@Table(name = "ventas")
@SQLDelete(sql = "UPDATE ventas SET estado = 0 WHERE id_venta = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private Integer idVenta;

	@Column(name = "id_sucursal")
	private Integer idSucursal;

	@Column(name = "id_pedido")
	private Integer idPedido;

	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "total_venta")
	private Double totalVenta;

	@Column(name = "tipo_comprobante")
	private String tipoComprobante;

	@Column(name = "numero_comprobante")
	private String numeroComprobante;

	@Column(name = "fecha_venta")
	private String fechaVenta;

	@Column(name = "estado")
	private Integer estado;

	public Venta() {
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", idSucursal=" + idSucursal + ", totalVenta=" + totalVenta
				+ ", fechaVenta=" + fechaVenta + ", estado=" + estado + "]";
	}

}
