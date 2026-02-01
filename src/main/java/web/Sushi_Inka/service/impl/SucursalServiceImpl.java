package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Sucursal;
import web.Sushi_Inka.repository.SucursalRepository;
import web.Sushi_Inka.service.ISucursalService;

@Service
public class SucursalServiceImpl implements ISucursalService {

	@Autowired
	private SucursalRepository sucursalRepository;

	@Override
	public List<Sucursal> obtenerTodos() {
		return sucursalRepository.findAll();
	}

	@Override
	public Sucursal obtenerPorId(Integer id) {
		return sucursalRepository.findById(id).orElse(null);
	}

	@Override
	public Sucursal guardar(Sucursal sucursal) {
		return sucursalRepository.save(sucursal);
	}

	@Override
	public Sucursal actualizar(Integer id, Sucursal sucursal) {
		Sucursal sucursalExistente = sucursalRepository.findById(id).orElse(null);
		if (sucursalExistente != null) {
			sucursalExistente.setIdRestaurante(sucursal.getIdRestaurante());
			sucursalExistente.setNombre(sucursal.getNombre());
			sucursalExistente.setDireccion(sucursal.getDireccion());
			sucursalExistente.setTelefono(sucursal.getTelefono());
			sucursalExistente.setEstado(sucursal.getEstado());
			return sucursalRepository.save(sucursalExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		sucursalRepository.deleteById(id);
	}

	@Override
	public List<Sucursal> obtenerPorRestaurante(Integer idRestaurante) {
		return sucursalRepository.findByIdRestaurante(idRestaurante);
	}

}
