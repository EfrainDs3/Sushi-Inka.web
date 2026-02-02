package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Plato;
import web.Sushi_Inka.repository.PlatoRepository;
import web.Sushi_Inka.service.IPlatoService;

@Service
public class PlatoService implements IPlatoService {

	@Autowired
	private PlatoRepository platoRepository;

	@Override
	public List<Plato> obtenerTodos() {
		return platoRepository.findAll();
	}

	@Override
	public Plato obtenerPorId(Integer id) {
		return platoRepository.findById(id).orElse(null);
	}

	@Override
	public Plato guardar(Plato plato) {
		return platoRepository.save(plato);
	}

	@Override
	public Plato actualizar(Integer id, Plato plato) {
		Plato platoExistente = platoRepository.findById(id).orElse(null);
		if (platoExistente != null) {
			platoExistente.setIdCategoria(plato.getIdCategoria());
			platoExistente.setIdSucursal(plato.getIdSucursal());
			platoExistente.setNombre(plato.getNombre());
			platoExistente.setDescripcion(plato.getDescripcion());
			platoExistente.setPrecio(plato.getPrecio());
			platoExistente.setEstado(plato.getEstado());
			return platoRepository.save(platoExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		platoRepository.deleteById(id);
	}

	@Override
	public List<Plato> obtenerPorCategoria(Integer idCategoria) {
		return platoRepository.findByIdCategoria(idCategoria);
	}

	@Override
	public List<Plato> obtenerPorSucursal(Integer idSucursal) {
		return platoRepository.findByIdSucursal(idSucursal);
	}

}



