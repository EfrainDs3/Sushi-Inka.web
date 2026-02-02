package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Mesa;

public interface IMesaService {
	
	List<Mesa> obtenerTodos();
	Mesa obtenerPorId(Integer id);
	Mesa guardar(Mesa mesa);
	Mesa actualizar(Integer id, Mesa mesa);
	void eliminar(Integer id);
	List<Mesa> obtenerPorSucursal(Integer idSucursal);

}

