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

import web.Sushi_Inka.entity.MovimientoCaja;
import web.Sushi_Inka.service.IMovimientoCajaService;

@RestController
@RequestMapping("/restful/movimientos-caja")
public class MovimientoCajaController {

	@Autowired
	private IMovimientoCajaService movimientoCajaService;

	@GetMapping
	public ResponseEntity<List<MovimientoCaja>> obtenerTodos() {
		List<MovimientoCaja> movimientos = movimientoCajaService.obtenerTodos();
		return ResponseEntity.ok(movimientos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovimientoCaja> obtenerPorId(@PathVariable Integer id) {
		MovimientoCaja movimiento = movimientoCajaService.obtenerPorId(id);
		if (movimiento != null) {
			return ResponseEntity.ok(movimiento);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/sesion/{idSesionCaja}")
	public ResponseEntity<List<MovimientoCaja>> obtenerPorSesionCaja(@PathVariable Integer idSesionCaja) {
		List<MovimientoCaja> movimientos = movimientoCajaService.obtenerPorSesionCaja(idSesionCaja);
		return ResponseEntity.ok(movimientos);
	}

	@PostMapping
	public ResponseEntity<MovimientoCaja> guardar(@RequestBody MovimientoCaja movimientoCaja) {
		MovimientoCaja nuevoMovimiento = movimientoCajaService.guardar(movimientoCaja);
		return ResponseEntity.ok(nuevoMovimiento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MovimientoCaja> actualizar(@PathVariable Integer id, @RequestBody MovimientoCaja movimientoCaja) {
		MovimientoCaja movimientoActualizado = movimientoCajaService.actualizar(id, movimientoCaja);
		if (movimientoActualizado != null) {
			return ResponseEntity.ok(movimientoActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		movimientoCajaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

