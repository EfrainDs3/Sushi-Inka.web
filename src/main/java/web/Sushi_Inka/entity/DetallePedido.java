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
@Table(name = "detalle_pedido")
@SQLDelete(sql = "UPDATE detalle_pedido SET estado = 0 WHERE id_detalle_pedido = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class DetallePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_pedido")
	private Integer idDetallePedido;

	@Column(name = "id_pedido")
	private Integer idPedido;

	@Column(name = "id_plato")
	private Integer idPlato;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "precio_unitario")
	private Double precioUnitario;

	@Column(name = "subtotal")
	private Double subtotal;

	@Column(name = "notas")
	private String notas;

	public DetallePedido() {
	}

	public Integer getIdDetallePedido() {
		return idDetallePedido;
	}

	public void setIdDetallePedido(Integer idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdPlato() {
		return idPlato;
	}

	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return "DetallePedido [idDetallePedido=" + idDetallePedido + ", idPedido=" + idPedido + ", idPlato="
				+ idPlato + ", cantidad=" + cantidad + ", subtotal=" + subtotal + "]";
	}

}

