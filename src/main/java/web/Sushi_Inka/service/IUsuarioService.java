package web.Sushi_Inka.service;

import java.util.List;
import java.util.Optional;

import web.Sushi_Inka.entity.Usuarios;

public interface IUsuarioService {

	List<Usuarios> getAllUsuarios();

	Optional<Usuarios> getUsuarioById(Integer id);

	Optional<Usuarios> getUsuarioByLogin(String login);

	List<Usuarios> getUsuariosBySucursal(Integer idSucursal);

	Usuarios registrarUsuario(Usuarios usuario);

	Optional<Usuarios> updateUsuario(Integer id, Usuarios usuario);

	void deleteUsuario(Integer id);

}
