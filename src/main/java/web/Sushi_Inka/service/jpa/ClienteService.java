package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Cliente;
import web.Sushi_Inka.repository.ClienteRepository;
import web.Sushi_Inka.service.IClienteService;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> obtenerTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente obtenerPorId(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public Cliente guardar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente actualizar(Integer id, Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
		if (clienteExistente != null) {
			clienteExistente.setIdSucursal(cliente.getIdSucursal());
			clienteExistente.setNombre(cliente.getNombre());
			clienteExistente.setApellidos(cliente.getApellidos());
			clienteExistente.setTelefono(cliente.getTelefono());
			clienteExistente.setEmail(cliente.getEmail());
			clienteExistente.setDni(cliente.getDni());
			clienteExistente.setEstado(cliente.getEstado());
			return clienteRepository.save(clienteExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public List<Cliente> obtenerPorSucursal(Integer idSucursal) {
		return clienteRepository.findByIdSucursal(idSucursal);
	}

}



