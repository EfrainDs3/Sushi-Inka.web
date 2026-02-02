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

import web.Sushi_Inka.entity.DetallePedido;
import web.Sushi_Inka.service.IDetallePedidoService;

@RestController
@RequestMapping("/restful/detalles-pedido")
public class DetallePedidoController {

	@Autowired
	private IDetallePedidoService detallePedidoService;

	@GetMapping
	public ResponseEntity<List<DetallePedido>> obtenerTodos() {
		List<DetallePedido> detalles = detallePedidoService.obtenerTodos();
		return ResponseEntity.ok(detalles);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetallePedido> obtenerPorId(@PathVariable Integer id) {
		DetallePedido detalle = detallePedidoService.obtenerPorId(id);
		if (detalle != null) {
			return ResponseEntity.ok(detalle);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/pedido/{idPedido}")
	public ResponseEntity<List<DetallePedido>> obtenerPorPedido(@PathVariable Integer idPedido) {
		List<DetallePedido> detalles = detallePedidoService.obtenerPorPedido(idPedido);
		return ResponseEntity.ok(detalles);
	}

	@PostMapping
	public ResponseEntity<DetallePedido> guardar(@RequestBody DetallePedido detallePedido) {
		DetallePedido nuevoDetalle = detallePedidoService.guardar(detallePedido);
		return ResponseEntity.ok(nuevoDetalle);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DetallePedido> actualizar(@PathVariable Integer id, @RequestBody DetallePedido detallePedido) {
		DetallePedido detalleActualizado = detallePedidoService.actualizar(id, detallePedido);
		if (detalleActualizado != null) {
			return ResponseEntity.ok(detalleActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		detallePedidoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

