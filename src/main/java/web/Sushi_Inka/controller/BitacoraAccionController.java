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

import web.Sushi_Inka.entity.BitacoraAccion;
import web.Sushi_Inka.service.IBitacoraAccionService;

@RestController
@RequestMapping("/restful/bitacoras-accion")
public class BitacoraAccionController {

	@Autowired
	private IBitacoraAccionService bitacoraAccionService;

	@GetMapping
	public ResponseEntity<List<BitacoraAccion>> obtenerTodos() {
		List<BitacoraAccion> bitacoras = bitacoraAccionService.obtenerTodos();
		return ResponseEntity.ok(bitacoras);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BitacoraAccion> obtenerPorId(@PathVariable Integer id) {
		BitacoraAccion bitacora = bitacoraAccionService.obtenerPorId(id);
		if (bitacora != null) {
			return ResponseEntity.ok(bitacora);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<BitacoraAccion>> obtenerPorUsuario(@PathVariable Integer idUsuario) {
		List<BitacoraAccion> bitacoras = bitacoraAccionService.obtenerPorUsuario(idUsuario);
		return ResponseEntity.ok(bitacoras);
	}

	@PostMapping
	public ResponseEntity<BitacoraAccion> guardar(@RequestBody BitacoraAccion bitacoraAccion) {
		BitacoraAccion nuevaBitacora = bitacoraAccionService.guardar(bitacoraAccion);
		return ResponseEntity.ok(nuevaBitacora);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BitacoraAccion> actualizar(@PathVariable Integer id, @RequestBody BitacoraAccion bitacoraAccion) {
		BitacoraAccion bitacoraActualizada = bitacoraAccionService.actualizar(id, bitacoraAccion);
		if (bitacoraActualizada != null) {
			return ResponseEntity.ok(bitacoraActualizada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		bitacoraAccionService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
