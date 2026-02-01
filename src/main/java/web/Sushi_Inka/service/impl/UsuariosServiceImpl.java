package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Usuarios;
import web.Sushi_Inka.repository.UsuariosRepository;
import web.Sushi_Inka.service.IUsuariosService;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Override
	public List<Usuarios> obtenerTodos() {
		return usuariosRepository.findAll();
	}

	@Override
	public Usuarios obtenerPorId(Integer id) {
		return usuariosRepository.findById(id).orElse(null);
	}

	@Override
	public Usuarios guardar(Usuarios usuarios) {
		return usuariosRepository.save(usuarios);
	}

	@Override
	public Usuarios actualizar(Integer id, Usuarios usuarios) {
		Usuarios usuarioExistente = usuariosRepository.findById(id).orElse(null);
		if (usuarioExistente != null) {
			usuarioExistente.setNombreUsuario(usuarios.getNombreUsuario());
			usuarioExistente.setApellidos(usuarios.getApellidos());
			usuarioExistente.setNombreUsuarioLogin(usuarios.getNombreUsuarioLogin());
			usuarioExistente.setContrasena(usuarios.getContrasena());
			usuarioExistente.setRolId(usuarios.getRolId());
			usuarioExistente.setIdSucursal(usuarios.getIdSucursal());
			usuarioExistente.setEstado(usuarios.getEstado());
			usuarioExistente.setUltimoLogin(usuarios.getUltimoLogin());
			return usuariosRepository.save(usuarioExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		usuariosRepository.deleteById(id);
	}

	@Override
	public Usuarios obtenerPorNombreUsuario(String nombreUsuarioLogin) {
		return usuariosRepository.findByNombreUsuarioLogin(nombreUsuarioLogin);
	}

}
