package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Restaurante;

public interface IRestauranteService {
	
	List<Restaurante> obtenerTodos();
	Restaurante obtenerPorId(Integer id);
	Restaurante guardar(Restaurante restaurante);
	Restaurante actualizar(Integer id, Restaurante restaurante);
	void eliminar(Integer id);

}
