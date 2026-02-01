package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Insumo;

public interface IInsumoService {
	
	List<Insumo> obtenerTodos();
	Insumo obtenerPorId(Integer id);
	Insumo guardar(Insumo insumo);
	Insumo actualizar(Integer id, Insumo insumo);
	void eliminar(Integer id);
	List<Insumo> obtenerPorSucursal(Integer idSucursal);

}
