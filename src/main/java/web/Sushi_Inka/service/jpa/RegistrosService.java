package web.Sushi_Inka.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.Sushi_Inka.entity.Registros;
import web.Sushi_Inka.repository.RegistrosRepository;
import web.Sushi_Inka.service.IRegistrosService;

@Service
@Transactional
public class RegistrosService implements IRegistrosService {

	@Autowired
	private RegistrosRepository registrosRepository;

	@Override
	public List<Registros> listar() {
		return registrosRepository.findAll();
	}

	@Override
	public Optional<Registros> obtenerPorId(Integer id) {
		return registrosRepository.findById(id);
	}

	@Override
	public Optional<Registros> obtenerPorEmail(String email) {
		return registrosRepository.findByEmail(email);
	}

	@Override
	public Optional<Registros> obtenerPorToken(String token) {
		return registrosRepository.findByAccessToken(token);
	}

	@Override
	public Registros crear(Registros registro) {
		return registrosRepository.save(registro);
	}

	@Override
	public Registros actualizar(Integer id, Registros registro) {
		Optional<Registros> existente = registrosRepository.findById(id);
		if (existente.isPresent()) {
			Registros reg = existente.get();
			if (registro.getNombres() != null) {
				reg.setNombres(registro.getNombres());
			}
			if (registro.getApellidos() != null) {
				reg.setApellidos(registro.getApellidos());
			}
			if (registro.getEmail() != null) {
				reg.setEmail(registro.getEmail());
			}
			if (registro.getLlave_secreta() != null) {
				reg.setLlave_secreta(registro.getLlave_secreta());
			}
			if (registro.getAccess_token() != null) {
				reg.setAccess_token(registro.getAccess_token());
			}
			if (registro.getEstado() != null) {
				reg.setEstado(registro.getEstado());
			}
			return registrosRepository.save(reg);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		registrosRepository.deleteById(id);
	}

}



