package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.AnulacionComprobante;

public interface IAnulacionComprobanteService {
	
	List<AnulacionComprobante> obtenerTodos();
	AnulacionComprobante obtenerPorId(Integer id);
	AnulacionComprobante guardar(AnulacionComprobante anulacionComprobante);
	AnulacionComprobante actualizar(Integer id, AnulacionComprobante anulacionComprobante);
	void eliminar(Integer id);
	List<AnulacionComprobante> obtenerPorVenta(Integer idVenta);

}

