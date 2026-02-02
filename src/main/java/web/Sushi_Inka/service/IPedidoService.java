package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Pedido;

public interface IPedidoService {
	
	List<Pedido> obtenerTodos();
	Pedido obtenerPorId(Integer id);
	Pedido guardar(Pedido pedido);
	Pedido actualizar(Integer id, Pedido pedido);
	void eliminar(Integer id);
	List<Pedido> obtenerPorSucursal(Integer idSucursal);
	List<Pedido> obtenerPorMesa(Integer idMesa);

}

