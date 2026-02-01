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

import web.Sushi_Inka.entity.Venta;
import web.Sushi_Inka.service.IVentaService;

@RestController
@RequestMapping("/restful/ventas")
public class VentaController {

	@Autowired
	private IVentaService ventaService;

	@GetMapping
	public ResponseEntity<List<Venta>> obtenerTodos() {
		List<Venta> ventas = ventaService.obtenerTodos();
		return ResponseEntity.ok(ventas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> obtenerPorId(@PathVariable Integer id) {
		Venta venta = ventaService.obtenerPorId(id);
		if (venta != null) {
			return ResponseEntity.ok(venta);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/sucursal/{idSucursal}")
	public ResponseEntity<List<Venta>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
		List<Venta> ventas = ventaService.obtenerPorSucursal(idSucursal);
		return ResponseEntity.ok(ventas);
	}

	@GetMapping("/pedido/{idPedido}")
	public ResponseEntity<List<Venta>> obtenerPorPedido(@PathVariable Integer idPedido) {
		List<Venta> ventas = ventaService.obtenerPorPedido(idPedido);
		return ResponseEntity.ok(ventas);
	}

	@PostMapping
	public ResponseEntity<Venta> guardar(@RequestBody Venta venta) {
		Venta nuevaVenta = ventaService.guardar(venta);
		return ResponseEntity.ok(nuevaVenta);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Venta> actualizar(@PathVariable Integer id, @RequestBody Venta venta) {
		Venta ventaActualizada = ventaService.actualizar(id, venta);
		if (ventaActualizada != null) {
			return ResponseEntity.ok(ventaActualizada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		ventaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
