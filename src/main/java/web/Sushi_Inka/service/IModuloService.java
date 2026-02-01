package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Modulo;

public interface IModuloService {
	
	List<Modulo> obtenerTodos();
	Modulo obtenerPorId(Integer id);
	Modulo guardar(Modulo modulo);
	Modulo actualizar(Integer id, Modulo modulo);
	void eliminar(Integer id);

}
