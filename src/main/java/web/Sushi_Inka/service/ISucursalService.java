package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Sucursal;

public interface ISucursalService {
	
	List<Sucursal> obtenerTodos();
	Sucursal obtenerPorId(Integer id);
	Sucursal guardar(Sucursal sucursal);
	Sucursal actualizar(Integer id, Sucursal sucursal);
	void eliminar(Integer id);
	List<Sucursal> obtenerPorRestaurante(Integer idRestaurante);

}

