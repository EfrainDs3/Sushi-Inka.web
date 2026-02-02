package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Perfil;
import web.Sushi_Inka.repository.PerfilRepository;
import web.Sushi_Inka.service.IPerfilService;

@Service
public class PerfilService implements IPerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public List<Perfil> obtenerTodos() {
		return perfilRepository.findAll();
	}

	@Override
	public Perfil obtenerPorId(Integer id) {
		return perfilRepository.findById(id).orElse(null);
	}

	@Override
	public Perfil guardar(Perfil perfil) {
		return perfilRepository.save(perfil);
	}

	@Override
	public Perfil actualizar(Integer id, Perfil perfil) {
		Perfil perfilExistente = perfilRepository.findById(id).orElse(null);
		if (perfilExistente != null) {
			perfilExistente.setNombrePerfil(perfil.getNombrePerfil());
			perfilExistente.setDescripcion(perfil.getDescripcion());
			perfilExistente.setEstado(perfil.getEstado());
			return perfilRepository.save(perfilExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		perfilRepository.deleteById(id);
	}

}



