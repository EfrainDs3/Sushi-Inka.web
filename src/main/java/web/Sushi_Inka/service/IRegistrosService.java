package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Registros;

public interface IRegistrosService {
	
	List<Registros> obtenerTodos();
	Registros obtenerPorId(Integer id);
	Registros guardar(Registros registros);
	Registros actualizar(Integer id, Registros registros);
	void eliminar(Integer id);
	Registros obtenerPorToken(String token);
	List<Registros> obtenerPorEmail(String email);

}
