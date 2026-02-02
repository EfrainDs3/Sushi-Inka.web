package web.Sushi_Inka.service;

import java.util.List;
import java.util.Optional;

import web.Sushi_Inka.entity.Registros;

public interface IRegistrosService {

	List<Registros> listar();

	Optional<Registros> obtenerPorId(Integer id);

	Optional<Registros> obtenerPorEmail(String email);

	Optional<Registros> obtenerPorToken(String token);

	Registros crear(Registros registro);

	Registros actualizar(Integer id, Registros registro);

	void eliminar(Integer id);

}

