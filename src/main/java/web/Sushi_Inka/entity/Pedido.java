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
@Table(name = "pedidos")
@SQLDelete(sql = "UPDATE pedidos SET estado_pedido = 'cancelado' WHERE id_pedido = ?")
@org.hibernate.annotations.SQLRestriction("estado_pedido != 'cancelado'")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;

	@Column(name = "id_sucursal")
	private Integer idSucursal;

	@Column(name = "id_mesa")
	private Integer idMesa;

	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "tipo_pedido")
	private String tipoPedido;

	@Column(name = "estado_pedido")
	private String estadoPedido;

	@Column(name = "total")
	private Double total;

	@Column(name = "fecha_hora")
	private String fechaHora;

	public Pedido() {
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Integer getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", idSucursal=" + idSucursal + ", estadoPedido=" + estadoPedido
				+ ", total=" + total + "]";
	}

}
