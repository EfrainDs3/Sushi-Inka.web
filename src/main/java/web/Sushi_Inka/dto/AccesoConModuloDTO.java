package web.Sushi_Inka.dto;

public class AccesoConModuloDTO {

    private Integer idAcceso;
    private Integer idModulo;
    private Integer idPerfil;
    private String nombreModulo;
    private String descripcionModulo;
    private Boolean puedeVer;
    private Boolean puedeCrear;
    private Boolean puedeEditar;
    private Boolean puedeEliminar;
    private Integer estado;

    // Constructor vacío
    public AccesoConModuloDTO() {
    }

    // Constructor con parámetros
    public AccesoConModuloDTO(Integer idAcceso, Integer idModulo, Integer idPerfil, 
                               String nombreModulo, String descripcionModulo,
                               Boolean puedeVer, Boolean puedeCrear, Boolean puedeEditar, 
                               Boolean puedeEliminar, Integer estado) {
        this.idAcceso = idAcceso;
        this.idModulo = idModulo;
        this.idPerfil = idPerfil;
        this.nombreModulo = nombreModulo;
        this.descripcionModulo = descripcionModulo;
        this.puedeVer = puedeVer;
        this.puedeCrear = puedeCrear;
        this.puedeEditar = puedeEditar;
        this.puedeEliminar = puedeEliminar;
        this.estado = estado;
    }

    // Getters y Setters
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

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getDescripcionModulo() {
        return descripcionModulo;
    }

    public void setDescripcionModulo(String descripcionModulo) {
        this.descripcionModulo = descripcionModulo;
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
        return "AccesoConModuloDTO{" +
                "idAcceso=" + idAcceso +
                ", idModulo=" + idModulo +
                ", idPerfil=" + idPerfil +
                ", nombreModulo='" + nombreModulo + '\'' +
                ", descripcionModulo='" + descripcionModulo + '\'' +
                ", puedeVer=" + puedeVer +
                ", puedeCrear=" + puedeCrear +
                ", puedeEditar=" + puedeEditar +
                ", puedeEliminar=" + puedeEliminar +
                ", estado=" + estado +
                '}';
    }
}
