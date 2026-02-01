package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Proveedor;
import web.Sushi_Inka.repository.ProveedorRepository;
import web.Sushi_Inka.service.IProveedorService;

@Service
public class ProveedorServiceImpl implements IProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;

	@Override
	public List<Proveedor> obtenerTodos() {
		return proveedorRepository.findAll();
	}

	@Override
	public Proveedor obtenerPorId(Integer id) {
		return proveedorRepository.findById(id).orElse(null);
	}

	@Override
	public Proveedor guardar(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}

	@Override
	public Proveedor actualizar(Integer id, Proveedor proveedor) {
		Proveedor proveedorExistente = proveedorRepository.findById(id).orElse(null);
		if (proveedorExistente != null) {
			proveedorExistente.setIdSucursal(proveedor.getIdSucursal());
			proveedorExistente.setNombre(proveedor.getNombre());
			proveedorExistente.setContacto(proveedor.getContacto());
			proveedorExistente.setTelefono(proveedor.getTelefono());
			proveedorExistente.setEmail(proveedor.getEmail());
			proveedorExistente.setDireccion(proveedor.getDireccion());
			proveedorExistente.setEstado(proveedor.getEstado());
			return proveedorRepository.save(proveedorExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		proveedorRepository.deleteById(id);
	}

	@Override
	public List<Proveedor> obtenerPorSucursal(Integer idSucursal) {
		return proveedorRepository.findByIdSucursal(idSucursal);
	}

}
