package web.Sushi_Inka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.Sushi_Inka.entity.Registros;
import web.Sushi_Inka.service.IRegistrosService;

@RestController
@RequestMapping("/restful")
public class RegistrosController {

	@Autowired
	private IRegistrosService registrosService;

	@GetMapping("/registros")
	public List<Registros> listar() {
		return registrosService.listar();
	}

	@GetMapping("/registros/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
		Optional<Registros> registro = registrosService.obtenerPorId(id);
		if (registro.isPresent()) {
			return ResponseEntity.ok(registro.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
	}

	@GetMapping("/registros/email/{email}")
	public ResponseEntity<?> obtenerPorEmail(@PathVariable String email) {
		Optional<Registros> registro = registrosService.obtenerPorEmail(email);
		if (registro.isPresent()) {
			return ResponseEntity.ok(registro.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
	}

	@GetMapping("/registros/token/{token}")
	public ResponseEntity<?> obtenerPorToken(@PathVariable String token) {
		Optional<Registros> registro = registrosService.obtenerPorToken(token);
		if (registro.isPresent()) {
			return ResponseEntity.ok(registro.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token no encontrado");
	}

	@PostMapping("/registros")
	public ResponseEntity<?> crear(@RequestBody Registros registro) {
		try {
			Registros nuevoRegistro = registrosService.crear(registro);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error al crear registro: " + e.getMessage());
		}
	}

	@PutMapping("/registros/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Registros registro) {
		try {
			Registros actualizado = registrosService.actualizar(id, registro);
			if (actualizado != null) {
				return ResponseEntity.ok(actualizado);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro no encontrado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error al actualizar: " + e.getMessage());
		}
	}

	@DeleteMapping("/registros/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		try {
			registrosService.eliminar(id);
			return ResponseEntity.ok("Registro eliminado correctamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error al eliminar: " + e.getMessage());
		}
	}

}

