package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.ComprobanteElectronico;

public interface IComprobanteElectronicoService {
	
	List<ComprobanteElectronico> obtenerTodos();
	ComprobanteElectronico obtenerPorId(Integer id);
	ComprobanteElectronico guardar(ComprobanteElectronico comprobanteElectronico);
	ComprobanteElectronico actualizar(Integer id, ComprobanteElectronico comprobanteElectronico);
	void eliminar(Integer id);
	List<ComprobanteElectronico> obtenerPorVenta(Integer idVenta);

}

