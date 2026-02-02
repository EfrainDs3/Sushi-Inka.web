package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Plato;

public interface IPlatoService {
	
	List<Plato> obtenerTodos();
	Plato obtenerPorId(Integer id);
	Plato guardar(Plato plato);
	Plato actualizar(Integer id, Plato plato);
	void eliminar(Integer id);
	List<Plato> obtenerPorCategoria(Integer idCategoria);
	List<Plato> obtenerPorSucursal(Integer idSucursal);

}

