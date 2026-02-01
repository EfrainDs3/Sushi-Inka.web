package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Restaurante;
import web.Sushi_Inka.repository.RestauranteRepository;
import web.Sushi_Inka.service.IRestauranteService;

@Service
public class RestauranteServiceImpl implements IRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Override
	public List<Restaurante> obtenerTodos() {
		return restauranteRepository.findAll();
	}

	@Override
	public Restaurante obtenerPorId(Integer id) {
		return restauranteRepository.findById(id).orElse(null);
	}

	@Override
	public Restaurante guardar(Restaurante restaurante) {
		return restauranteRepository.save(restaurante);
	}

	@Override
	public Restaurante actualizar(Integer id, Restaurante restaurante) {
		Restaurante restauranteExistente = restauranteRepository.findById(id).orElse(null);
		if (restauranteExistente != null) {
			restauranteExistente.setNombre(restaurante.getNombre());
			restauranteExistente.setDireccion(restaurante.getDireccion());
			restauranteExistente.setTelefono(restaurante.getTelefono());
			restauranteExistente.setEmail(restaurante.getEmail());
			restauranteExistente.setRuc(restaurante.getRuc());
			restauranteExistente.setEstado(restaurante.getEstado());
			restauranteExistente.setFechaVencimiento(restaurante.getFechaVencimiento());
			return restauranteRepository.save(restauranteExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		restauranteRepository.deleteById(id);
	}

}
