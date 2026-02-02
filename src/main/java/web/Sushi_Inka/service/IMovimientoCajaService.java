package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.MovimientoCaja;

public interface IMovimientoCajaService {
	
	List<MovimientoCaja> obtenerTodos();
	MovimientoCaja obtenerPorId(Integer id);
	MovimientoCaja guardar(MovimientoCaja movimientoCaja);
	MovimientoCaja actualizar(Integer id, MovimientoCaja movimientoCaja);
	void eliminar(Integer id);
	List<MovimientoCaja> obtenerPorSesionCaja(Integer idSesionCaja);

}

