package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Registros;
import web.Sushi_Inka.repository.RegistrosRepository;
import web.Sushi_Inka.service.IRegistrosService;

@Service
public class RegistrosServiceImpl implements IRegistrosService {

	@Autowired
	private RegistrosRepository registrosRepository;

	@Override
	public List<Registros> obtenerTodos() {
		return registrosRepository.findAll();
	}

	@Override
	public Registros obtenerPorId(Integer id) {
		return registrosRepository.findById(id).orElse(null);
	}

	@Override
	public Registros guardar(Registros registros) {
		return registrosRepository.save(registros);
	}

	@Override
	public Registros actualizar(Integer id, Registros registros) {
		Registros registrosExistente = registrosRepository.findById(id).orElse(null);
		if (registrosExistente != null) {
			registrosExistente.setEmail(registros.getEmail());
			registrosExistente.setToken(registros.getToken());
			registrosExistente.setTipoRegistro(registros.getTipoRegistro());
			registrosExistente.setFechaSolicitud(registros.getFechaSolicitud());
			registrosExistente.setFechaExpiracion(registros.getFechaExpiracion());
			registrosExistente.setEstado(registros.getEstado());
			return registrosRepository.save(registrosExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		registrosRepository.deleteById(id);
	}

	@Override
	public Registros obtenerPorToken(String token) {
		return registrosRepository.findByToken(token);
	}

	@Override
	public List<Registros> obtenerPorEmail(String email) {
		return registrosRepository.findByEmail(email);
	}

}
