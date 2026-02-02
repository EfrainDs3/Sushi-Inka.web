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

import web.Sushi_Inka.entity.PagoSuscripcion;
import web.Sushi_Inka.service.IPagoSuscripcionService;

@RestController
@RequestMapping("/restful/pagos-suscripcion")
public class PagoSuscripcionController {

	@Autowired
	private IPagoSuscripcionService pagoSuscripcionService;

	@GetMapping
	public ResponseEntity<List<PagoSuscripcion>> obtenerTodos() {
		List<PagoSuscripcion> pagos = pagoSuscripcionService.obtenerTodos();
		return ResponseEntity.ok(pagos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PagoSuscripcion> obtenerPorId(@PathVariable Integer id) {
		PagoSuscripcion pago = pagoSuscripcionService.obtenerPorId(id);
		if (pago != null) {
			return ResponseEntity.ok(pago);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/restaurante/{idRestaurante}")
	public ResponseEntity<List<PagoSuscripcion>> obtenerPorRestaurante(@PathVariable Integer idRestaurante) {
		List<PagoSuscripcion> pagos = pagoSuscripcionService.obtenerPorRestaurante(idRestaurante);
		return ResponseEntity.ok(pagos);
	}

	@PostMapping
	public ResponseEntity<PagoSuscripcion> guardar(@RequestBody PagoSuscripcion pagoSuscripcion) {
		PagoSuscripcion nuevoPago = pagoSuscripcionService.guardar(pagoSuscripcion);
		return ResponseEntity.ok(nuevoPago);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PagoSuscripcion> actualizar(@PathVariable Integer id, @RequestBody PagoSuscripcion pagoSuscripcion) {
		PagoSuscripcion pagoActualizado = pagoSuscripcionService.actualizar(id, pagoSuscripcion);
		if (pagoActualizado != null) {
			return ResponseEntity.ok(pagoActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		pagoSuscripcionService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

