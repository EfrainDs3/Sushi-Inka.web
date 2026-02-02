package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.DetallePedido;

public interface IDetallePedidoService {
	
	List<DetallePedido> obtenerTodos();
	DetallePedido obtenerPorId(Integer id);
	DetallePedido guardar(DetallePedido detallePedido);
	DetallePedido actualizar(Integer id, DetallePedido detallePedido);
	void eliminar(Integer id);
	List<DetallePedido> obtenerPorPedido(Integer idPedido);

}

