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

import web.Sushi_Inka.entity.MovimientoInventario;
import web.Sushi_Inka.service.IMovimientoInventarioService;

@RestController
@RequestMapping("/restful/movimientos-inventario")
public class MovimientoInventarioController {

	@Autowired
	private IMovimientoInventarioService movimientoInventarioService;

	@GetMapping
	public ResponseEntity<List<MovimientoInventario>> obtenerTodos() {
		List<MovimientoInventario> movimientos = movimientoInventarioService.obtenerTodos();
		return ResponseEntity.ok(movimientos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovimientoInventario> obtenerPorId(@PathVariable Integer id) {
		MovimientoInventario movimiento = movimientoInventarioService.obtenerPorId(id);
		if (movimiento != null) {
			return ResponseEntity.ok(movimiento);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/insumo/{idInsumo}")
	public ResponseEntity<List<MovimientoInventario>> obtenerPorInsumo(@PathVariable Integer idInsumo) {
		List<MovimientoInventario> movimientos = movimientoInventarioService.obtenerPorInsumo(idInsumo);
		return ResponseEntity.ok(movimientos);
	}

	@PostMapping
	public ResponseEntity<MovimientoInventario> guardar(@RequestBody MovimientoInventario movimientoInventario) {
		MovimientoInventario nuevoMovimiento = movimientoInventarioService.guardar(movimientoInventario);
		return ResponseEntity.ok(nuevoMovimiento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MovimientoInventario> actualizar(@PathVariable Integer id, @RequestBody MovimientoInventario movimientoInventario) {
		MovimientoInventario movimientoActualizado = movimientoInventarioService.actualizar(id, movimientoInventario);
		if (movimientoActualizado != null) {
			return ResponseEntity.ok(movimientoActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		movimientoInventarioService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
