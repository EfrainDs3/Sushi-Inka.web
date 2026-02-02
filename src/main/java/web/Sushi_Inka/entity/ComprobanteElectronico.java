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
@Table(name = "comprobante_electronico")
@SQLDelete(sql = "UPDATE comprobante_electronico SET estado = 0 WHERE id_comprobante_electronico = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class ComprobanteElectronico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comprobante_electronico")
	private Integer idComprobanteElectronico;

	@Column(name = "id_venta")
	private Integer idVenta;

	@Column(name = "numero_cdr")
	private String numeroCdr;

	@Column(name = "xml_path")
	private String xmlPath;

	@Column(name = "estado_envio")
	private String estadoEnvio;

	@Column(name = "respuesta_sunat")
	private String respuestaSunat;

	@Column(name = "fecha_envio")
	private String fechaEnvio;

	@Column(name = "estado")
	private Integer estado;

	public ComprobanteElectronico() {
	}

	public Integer getIdComprobanteElectronico() {
		return idComprobanteElectronico;
	}

	public void setIdComprobanteElectronico(Integer idComprobanteElectronico) {
		this.idComprobanteElectronico = idComprobanteElectronico;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public String getNumeroCdr() {
		return numeroCdr;
	}

	public void setNumeroCdr(String numeroCdr) {
		this.numeroCdr = numeroCdr;
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public String getEstadoEnvio() {
		return estadoEnvio;
	}

	public void setEstadoEnvio(String estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}

	public String getRespuestaSunat() {
		return respuestaSunat;
	}

	public void setRespuestaSunat(String respuestaSunat) {
		this.respuestaSunat = respuestaSunat;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ComprobanteElectronico [idComprobanteElectronico=" + idComprobanteElectronico + ", idVenta=" + idVenta
				+ ", numeroCdr=" + numeroCdr + ", estadoEnvio=" + estadoEnvio + ", estado=" + estado + "]";
	}

}

