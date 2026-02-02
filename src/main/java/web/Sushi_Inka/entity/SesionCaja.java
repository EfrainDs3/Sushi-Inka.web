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
@Table(name = "sesiones_caja")
@SQLDelete(sql = "UPDATE sesiones_caja SET estado = 0 WHERE id_sesion_caja = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class SesionCaja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sesion_caja")
	private Integer idSesionCaja;

	@Column(name = "id_sucursal")
	private Integer idSucursal;

	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "saldo_inicial")
	private Double saldoInicial;

	@Column(name = "saldo_final")
	private Double saldoFinal;

	@Column(name = "total_movimientos")
	private Double totalMovimientos;

	@Column(name = "fecha_apertura")
	private String fechaApertura;

	@Column(name = "fecha_cierre")
	private String fechaCierre;

	@Column(name = "estado")
	private Integer estado;

	public SesionCaja() {
	}

	public Integer getIdSesionCaja() {
		return idSesionCaja;
	}

	public void setIdSesionCaja(Integer idSesionCaja) {
		this.idSesionCaja = idSesionCaja;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(Double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public Double getTotalMovimientos() {
		return totalMovimientos;
	}

	public void setTotalMovimientos(Double totalMovimientos) {
		this.totalMovimientos = totalMovimientos;
	}

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "SesionCaja [idSesionCaja=" + idSesionCaja + ", idSucursal=" + idSucursal + ", saldoInicial="
				+ saldoInicial + ", saldoFinal=" + saldoFinal + ", estado=" + estado + "]";
	}

}
