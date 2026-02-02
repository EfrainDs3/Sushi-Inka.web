package web.Sushi_Inka.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Usuarios;
import web.Sushi_Inka.repository.UsuariosRepository;
import web.Sushi_Inka.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Override
	public List<Usuarios> getAllUsuarios() {
		return usuariosRepository.findAll();
	}

	@Override
	public Optional<Usuarios> getUsuarioById(Integer id) {
		return usuariosRepository.findById(id);
	}

	@Override
	public Optional<Usuarios> getUsuarioByLogin(String login) {
		List<Usuarios> usuarios = usuariosRepository.findAll();
		return usuarios.stream()
				.filter(u -> u.getNombreUsuarioLogin() != null && u.getNombreUsuarioLogin().equals(login))
				.findFirst();
	}

	@Override
	public List<Usuarios> getUsuariosBySucursal(Integer idSucursal) {
		List<Usuarios> usuarios = usuariosRepository.findAll();
		return usuarios.stream()
				.filter(u -> u.getIdSucursal() != null && u.getIdSucursal().equals(idSucursal))
				.toList();
	}

	@Override
	public Usuarios registrarUsuario(Usuarios usuario) {
		return usuariosRepository.save(usuario);
	}

	@Override
	public Optional<Usuarios> updateUsuario(Integer id, Usuarios usuario) {
		Optional<Usuarios> usuarioExistente = usuariosRepository.findById(id);
		if (usuarioExistente.isPresent()) {
			Usuarios u = usuarioExistente.get();
			u.setNombreUsuario(usuario.getNombreUsuario());
			u.setApellidos(usuario.getApellidos());
			u.setNombreUsuarioLogin(usuario.getNombreUsuarioLogin());
			u.setContrasena(usuario.getContrasena());
			u.setRolId(usuario.getRolId());
			u.setIdSucursal(usuario.getIdSucursal());
			u.setEstado(usuario.getEstado());
			return Optional.of(usuariosRepository.save(u));
		}
		return Optional.empty();
	}

	@Override
	public void deleteUsuario(Integer id) {
		usuariosRepository.deleteById(id);
	}

}



