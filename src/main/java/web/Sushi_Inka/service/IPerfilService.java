package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Perfil;

public interface IPerfilService {
	
	List<Perfil> obtenerTodos();
	Perfil obtenerPorId(Integer id);
	Perfil guardar(Perfil perfil);
	Perfil actualizar(Integer id, Perfil perfil);
	void eliminar(Integer id);

}

