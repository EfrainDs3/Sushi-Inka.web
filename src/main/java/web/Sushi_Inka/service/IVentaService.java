package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Venta;

public interface IVentaService {
	
	List<Venta> obtenerTodos();
	Venta obtenerPorId(Integer id);
	Venta guardar(Venta venta);
	Venta actualizar(Integer id, Venta venta);
	void eliminar(Integer id);
	List<Venta> obtenerPorSucursal(Integer idSucursal);
	List<Venta> obtenerPorPedido(Integer idPedido);

}

