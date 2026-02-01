package web.Sushi_Inka.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations. Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "acceso")
@SQLDelete(sql = "UPDATE acceso SET estado = 0 WHERE id_acceso = ?")
@Where(clause = "estado = 1")
public class Acceso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_acceso")
	private Integer idAcceso;

	@Column(name = "id_modulo")
	private Integer idModulo;

	@Column(name = "id_perfil")
	private Integer idPerfil;

	@Column(name = "puede_ver")
	private Boolean puedeVer;

	@Column(name = "puede_crear")
	private Boolean puedeCrear;

	@Column(name = "puede_editar")
	private Boolean puedeEditar;

	@Column(name = "puede_eliminar")
	private Boolean puedeEliminar;

	@Column(name = "estado")
	private Integer estado = 1;

	public Acceso() {
	}

	public Integer getIdAcceso() {
		return idAcceso;
	}

	public void setIdAcceso(Integer idAcceso) {
		this.idAcceso = idAcceso;
	}

	public Integer getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Boolean getPuedeVer() {
		return puedeVer;
	}

	public void setPuedeVer(Boolean puedeVer) {
		this.puedeVer = puedeVer;
	}

	public Boolean getPuedeCrear() {
		return puedeCrear;
	}

	public void setPuedeCrear(Boolean puedeCrear) {
		this.puedeCrear = puedeCrear;
	}

	public Boolean getPuedeEditar() {
		return puedeEditar;
	}

	public void setPuedeEditar(Boolean puedeEditar) {
		this.puedeEditar = puedeEditar;
	}

	public Boolean getPuedeEliminar() {
		return puedeEliminar;
	}

	public void setPuedeEliminar(Boolean puedeEliminar) {
		this.puedeEliminar = puedeEliminar;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Acceso [idAcceso=" + idAcceso + ", idModulo=" + idModulo + ", idPerfil=" + idPerfil 
				+ ", puedeVer=" + puedeVer + ", puedeCrear=" + puedeCrear + ", puedeEditar=" + puedeEditar 
				+ ", puedeEliminar=" + puedeEliminar + ", estado=" + estado + "]";
	}

}
