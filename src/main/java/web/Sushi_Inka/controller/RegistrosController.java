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

import web.Sushi_Inka.entity.Registros;
import web.Sushi_Inka.service.IRegistrosService;

@RestController
@RequestMapping("/restful/registros")
public class RegistrosController {

	@Autowired
	private IRegistrosService registrosService;

	@GetMapping
	public ResponseEntity<List<Registros>> obtenerTodos() {
		List<Registros> registros = registrosService.obtenerTodos();
		return ResponseEntity.ok(registros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Registros> obtenerPorId(@PathVariable Integer id) {
		Registros registro = registrosService.obtenerPorId(id);
		if (registro != null) {
			return ResponseEntity.ok(registro);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/token/{token}")
	public ResponseEntity<List<Registros>> obtenerPorToken(@PathVariable String token) {
		List<Registros> registros = registrosService.obtenerPorToken(token);
		return ResponseEntity.ok(registros);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<List<Registros>> obtenerPorEmail(@PathVariable String email) {
		List<Registros> registros = registrosService.obtenerPorEmail(email);
		return ResponseEntity.ok(registros);
	}

	@PostMapping
	public ResponseEntity<Registros> guardar(@RequestBody Registros registros) {
		Registros nuevoRegistro = registrosService.guardar(registros);
		return ResponseEntity.ok(nuevoRegistro);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Registros> actualizar(@PathVariable Integer id, @RequestBody Registros registros) {
		Registros registroActualizado = registrosService.actualizar(id, registros);
		if (registroActualizado != null) {
			return ResponseEntity.ok(registroActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		registrosService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
