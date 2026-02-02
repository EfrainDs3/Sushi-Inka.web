package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.SesionCaja;

public interface ISesionCajaService {
	
	List<SesionCaja> obtenerTodos();
	SesionCaja obtenerPorId(Integer id);
	SesionCaja guardar(SesionCaja sesionCaja);
	SesionCaja actualizar(Integer id, SesionCaja sesionCaja);
	void eliminar(Integer id);
	List<SesionCaja> obtenerPorSucursal(Integer idSucursal);

}

