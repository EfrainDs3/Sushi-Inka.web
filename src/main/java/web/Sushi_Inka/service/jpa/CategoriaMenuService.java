package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.CategoriaMenu;
import web.Sushi_Inka.repository.CategoriaMenuRepository;
import web.Sushi_Inka.service.ICategoriaMenuService;

@Service
public class CategoriaMenuService implements ICategoriaMenuService {

	@Autowired
	private CategoriaMenuRepository categoriaMenuRepository;

	@Override
	public List<CategoriaMenu> obtenerTodos() {
		return categoriaMenuRepository.findAll();
	}

	@Override
	public CategoriaMenu obtenerPorId(Integer id) {
		return categoriaMenuRepository.findById(id).orElse(null);
	}

	@Override
	public CategoriaMenu guardar(CategoriaMenu categoriaMenu) {
		return categoriaMenuRepository.save(categoriaMenu);
	}

	@Override
	public CategoriaMenu actualizar(Integer id, CategoriaMenu categoriaMenu) {
		CategoriaMenu categoriaExistente = categoriaMenuRepository.findById(id).orElse(null);
		if (categoriaExistente != null) {
			categoriaExistente.setNombre(categoriaMenu.getNombre());
			categoriaExistente.setDescripcion(categoriaMenu.getDescripcion());
			categoriaExistente.setEstado(categoriaMenu.getEstado());
			return categoriaMenuRepository.save(categoriaExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		categoriaMenuRepository.deleteById(id);
	}

}



