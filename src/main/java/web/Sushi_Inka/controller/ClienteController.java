package web.Sushi_Inka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.Sushi_Inka.entity.Cliente;
import web.Sushi_Inka.service.IClienteService;

@RestController
@RequestMapping("/restful/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> obtenerTodos() {
		List<Cliente> clientes = clienteService.obtenerTodos();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerPorId(@PathVariable Integer id) {
		Cliente cliente = clienteService.obtenerPorId(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/sucursal/{idSucursal}")
	public ResponseEntity<List<Cliente>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
		List<Cliente> clientes = clienteService.obtenerPorSucursal(idSucursal);
		return ResponseEntity.ok(clientes);
	}

	@PostMapping
	public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
		Cliente nuevoCliente = clienteService.guardar(cliente);
		return ResponseEntity.ok(nuevoCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
		Cliente clienteActualizado = clienteService.actualizar(id, cliente);
		if (clienteActualizado != null) {
			return ResponseEntity.ok(clienteActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		clienteService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

