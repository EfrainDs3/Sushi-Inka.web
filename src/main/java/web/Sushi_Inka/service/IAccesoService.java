package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Acceso;

public interface IAccesoService {
	
	List<Acceso> obtenerTodos();
	Acceso obtenerPorId(Integer id);
	Acceso guardar(Acceso acceso);
	Acceso actualizar(Integer id, Acceso acceso);
	void eliminar(Integer id);
	List<Acceso> obtenerAccesosPorPerfil(Integer idPerfil);

}

