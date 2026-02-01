package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.CategoriaGasto;

public interface ICategoriaGastoService {
	
	List<CategoriaGasto> obtenerTodos();
	CategoriaGasto obtenerPorId(Integer id);
	CategoriaGasto guardar(CategoriaGasto categoriaGasto);
	CategoriaGasto actualizar(Integer id, CategoriaGasto categoriaGasto);
	void eliminar(Integer id);

}
