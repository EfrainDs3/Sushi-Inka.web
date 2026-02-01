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

import web.Sushi_Inka.entity.Modulo;
import web.Sushi_Inka.service.IModuloService;

@RestController
@RequestMapping("/restful/modulos")
public class ModuloController {

	@Autowired
	private IModuloService moduloService;

	@GetMapping
	public ResponseEntity<List<Modulo>> obtenerTodos() {
		List<Modulo> modulos = moduloService.obtenerTodos();
		return ResponseEntity.ok(modulos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Modulo> obtenerPorId(@PathVariable Integer id) {
		Modulo modulo = moduloService.obtenerPorId(id);
		if (modulo != null) {
			return ResponseEntity.ok(modulo);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Modulo> guardar(@RequestBody Modulo modulo) {
		Modulo nuevoModulo = moduloService.guardar(modulo);
		return ResponseEntity.ok(nuevoModulo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Modulo> actualizar(@PathVariable Integer id, @RequestBody Modulo modulo) {
		Modulo moduloActualizado = moduloService.actualizar(id, modulo);
		if (moduloActualizado != null) {
			return ResponseEntity.ok(moduloActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		moduloService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
