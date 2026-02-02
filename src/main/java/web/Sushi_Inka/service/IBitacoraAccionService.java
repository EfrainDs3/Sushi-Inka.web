package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.BitacoraAccion;

public interface IBitacoraAccionService {
	
	List<BitacoraAccion> obtenerTodos();
	BitacoraAccion obtenerPorId(Integer id);
	BitacoraAccion guardar(BitacoraAccion bitacoraAccion);
	BitacoraAccion actualizar(Integer id, BitacoraAccion bitacoraAccion);
	void eliminar(Integer id);
	List<BitacoraAccion> obtenerPorUsuario(Integer idUsuario);

}

