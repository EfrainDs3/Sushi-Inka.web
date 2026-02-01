package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Modulo;
import web.Sushi_Inka.repository.ModuloRepository;
import web.Sushi_Inka.service.IModuloService;

@Service
public class ModuloServiceImpl implements IModuloService {

	@Autowired
	private ModuloRepository moduloRepository;

	@Override
	public List<Modulo> obtenerTodos() {
		return moduloRepository.findAll();
	}

	@Override
	public Modulo obtenerPorId(Integer id) {
		return moduloRepository.findById(id).orElse(null);
	}

	@Override
	public Modulo guardar(Modulo modulo) {
		return moduloRepository.save(modulo);
	}

	@Override
	public Modulo actualizar(Integer id, Modulo modulo) {
		Modulo moduloExistente = moduloRepository.findById(id).orElse(null);
		if (moduloExistente != null) {
			moduloExistente.setNombre(modulo.getNombre());
			moduloExistente.setDescripcion(modulo.getDescripcion());
			moduloExistente.setEstado(modulo.getEstado());
			return moduloRepository.save(moduloExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		moduloRepository.deleteById(id);
	}

}
