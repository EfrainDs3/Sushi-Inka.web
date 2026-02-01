package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.CategoriaGasto;
import web.Sushi_Inka.repository.CategoriaGastoRepository;
import web.Sushi_Inka.service.ICategoriaGastoService;

@Service
public class CategoriaGastoServiceImpl implements ICategoriaGastoService {

	@Autowired
	private CategoriaGastoRepository categoriaGastoRepository;

	@Override
	public List<CategoriaGasto> obtenerTodos() {
		return categoriaGastoRepository.findAll();
	}

	@Override
	public CategoriaGasto obtenerPorId(Integer id) {
		return categoriaGastoRepository.findById(id).orElse(null);
	}

	@Override
	public CategoriaGasto guardar(CategoriaGasto categoriaGasto) {
		return categoriaGastoRepository.save(categoriaGasto);
	}

	@Override
	public CategoriaGasto actualizar(Integer id, CategoriaGasto categoriaGasto) {
		CategoriaGasto categoriaGastoExistente = categoriaGastoRepository.findById(id).orElse(null);
		if (categoriaGastoExistente != null) {
			categoriaGastoExistente.setNombre(categoriaGasto.getNombre());
			categoriaGastoExistente.setDescripcion(categoriaGasto.getDescripcion());
			categoriaGastoExistente.setEstado(categoriaGasto.getEstado());
			return categoriaGastoRepository.save(categoriaGastoExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		categoriaGastoRepository.deleteById(id);
	}

}
