package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Proveedor;

public interface IProveedorService {
	
	List<Proveedor> obtenerTodos();
	Proveedor obtenerPorId(Integer id);
	Proveedor guardar(Proveedor proveedor);
	Proveedor actualizar(Integer id, Proveedor proveedor);
	void eliminar(Integer id);
	List<Proveedor> obtenerPorSucursal(Integer idSucursal);

}

