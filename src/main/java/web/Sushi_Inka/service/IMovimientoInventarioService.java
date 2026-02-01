package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.MovimientoInventario;

public interface IMovimientoInventarioService {
	
	List<MovimientoInventario> obtenerTodos();
	MovimientoInventario obtenerPorId(Integer id);
	MovimientoInventario guardar(MovimientoInventario movimientoInventario);
	MovimientoInventario actualizar(Integer id, MovimientoInventario movimientoInventario);
	void eliminar(Integer id);
	List<MovimientoInventario> obtenerPorInsumo(Integer idInsumo);

}
