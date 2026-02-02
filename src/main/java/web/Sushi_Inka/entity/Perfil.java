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
@Table(name = "perfil")
@SQLDelete(sql = "UPDATE perfil SET estado = 0 WHERE id_perfil = ?")
@org.hibernate.annotations.SQLRestriction("estado = 1")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private Integer idPerfil;

	@Column(name = "nombre_perfil")
	private String nombrePerfil;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estado")
	private Integer estado = 1;

	public Perfil() {
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", nombrePerfil=" + nombrePerfil + ", estado=" + estado + "]";
	}

}

