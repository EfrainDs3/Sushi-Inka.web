package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Usuarios;

public interface IUsuariosService {
	
	List<Usuarios> obtenerTodos();
	Usuarios obtenerPorId(Integer id);
	Usuarios guardar(Usuarios usuarios);
	Usuarios actualizar(Integer id, Usuarios usuarios);
	void eliminar(Integer id);
	Usuarios obtenerPorNombreUsuario(String nombreUsuarioLogin);

}

