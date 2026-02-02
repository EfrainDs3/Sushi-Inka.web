package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.CategoriaMenu;

public interface ICategoriaMenuService {
	
	List<CategoriaMenu> obtenerTodos();
	CategoriaMenu obtenerPorId(Integer id);
	CategoriaMenu guardar(CategoriaMenu categoriaMenu);
	CategoriaMenu actualizar(Integer id, CategoriaMenu categoriaMenu);
	void eliminar(Integer id);

}

