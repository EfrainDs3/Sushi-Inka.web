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

import web.Sushi_Inka.entity.Pedido;
import web.Sushi_Inka.service.IPedidoService;

@RestController
@RequestMapping("/restful/pedidos")
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> obtenerTodos() {
		List<Pedido> pedidos = pedidoService.obtenerTodos();
		return ResponseEntity.ok(pedidos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> obtenerPorId(@PathVariable Integer id) {
		Pedido pedido = pedidoService.obtenerPorId(id);
		if (pedido != null) {
			return ResponseEntity.ok(pedido);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/sucursal/{idSucursal}")
	public ResponseEntity<List<Pedido>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
		List<Pedido> pedidos = pedidoService.obtenerPorSucursal(idSucursal);
		return ResponseEntity.ok(pedidos);
	}

	@GetMapping("/mesa/{idMesa}")
	public ResponseEntity<List<Pedido>> obtenerPorMesa(@PathVariable Integer idMesa) {
		List<Pedido> pedidos = pedidoService.obtenerPorMesa(idMesa);
		return ResponseEntity.ok(pedidos);
	}

	@PostMapping
	public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido) {
		Pedido nuevoPedido = pedidoService.guardar(pedido);
		return ResponseEntity.ok(nuevoPedido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedido> actualizar(@PathVariable Integer id, @RequestBody Pedido pedido) {
		Pedido pedidoActualizado = pedidoService.actualizar(id, pedido);
		if (pedidoActualizado != null) {
			return ResponseEntity.ok(pedidoActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		pedidoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
