package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.PagoSuscripcion;

public interface IPagoSuscripcionService {
	
	List<PagoSuscripcion> obtenerTodos();
	PagoSuscripcion obtenerPorId(Integer id);
	PagoSuscripcion guardar(PagoSuscripcion pagoSuscripcion);
	PagoSuscripcion actualizar(Integer id, PagoSuscripcion pagoSuscripcion);
	void eliminar(Integer id);
	List<PagoSuscripcion> obtenerPorRestaurante(Integer idRestaurante);

}
