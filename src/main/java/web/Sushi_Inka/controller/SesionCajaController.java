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

import web.Sushi_Inka.entity.SesionCaja;
import web.Sushi_Inka.service.ISesionCajaService;

@RestController
@RequestMapping("/restful/sesiones-caja")
public class SesionCajaController {

	@Autowired
	private ISesionCajaService sesionCajaService;

	@GetMapping
	public ResponseEntity<List<SesionCaja>> obtenerTodos() {
		List<SesionCaja> sesiones = sesionCajaService.obtenerTodos();
		return ResponseEntity.ok(sesiones);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SesionCaja> obtenerPorId(@PathVariable Integer id) {
		SesionCaja sesion = sesionCajaService.obtenerPorId(id);
		if (sesion != null) {
			return ResponseEntity.ok(sesion);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/sucursal/{idSucursal}")
	public ResponseEntity<List<SesionCaja>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
		List<SesionCaja> sesiones = sesionCajaService.obtenerPorSucursal(idSucursal);
		return ResponseEntity.ok(sesiones);
	}

	@PostMapping
	public ResponseEntity<SesionCaja> guardar(@RequestBody SesionCaja sesionCaja) {
		SesionCaja nuevaSesion = sesionCajaService.guardar(sesionCaja);
		return ResponseEntity.ok(nuevaSesion);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SesionCaja> actualizar(@PathVariable Integer id, @RequestBody SesionCaja sesionCaja) {
		SesionCaja sesionActualizada = sesionCajaService.actualizar(id, sesionCaja);
		if (sesionActualizada != null) {
			return ResponseEntity.ok(sesionActualizada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		sesionCajaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

