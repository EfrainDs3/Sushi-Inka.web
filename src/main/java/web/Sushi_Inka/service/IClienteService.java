package web.Sushi_Inka.service;

import java.util.List;

import web.Sushi_Inka.entity.Cliente;

public interface IClienteService {
	
	List<Cliente> obtenerTodos();
	Cliente obtenerPorId(Integer id);
	Cliente guardar(Cliente cliente);
	Cliente actualizar(Integer id, Cliente cliente);
	void eliminar(Integer id);
	List<Cliente> obtenerPorSucursal(Integer idSucursal);

}

