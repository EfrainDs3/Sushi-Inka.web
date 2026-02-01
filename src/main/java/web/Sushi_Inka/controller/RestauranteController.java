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

import web.Sushi_Inka.entity.Restaurante;
import web.Sushi_Inka.service.IRestauranteService;

@RestController
@RequestMapping("/restful/restaurantes")
public class RestauranteController {

	@Autowired
	private IRestauranteService restauranteService;

	@GetMapping
	public ResponseEntity<List<Restaurante>> obtenerTodos() {
		List<Restaurante> restaurantes = restauranteService.obtenerTodos();
		return ResponseEntity.ok(restaurantes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> obtenerPorId(@PathVariable Integer id) {
		Restaurante restaurante = restauranteService.obtenerPorId(id);
		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Restaurante> guardar(@RequestBody Restaurante restaurante) {
		Restaurante nuevoRestaurante = restauranteService.guardar(restaurante);
		return ResponseEntity.ok(nuevoRestaurante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> actualizar(@PathVariable Integer id, @RequestBody Restaurante restaurante) {
		Restaurante restauranteActualizado = restauranteService.actualizar(id, restaurante);
		if (restauranteActualizado != null) {
			return ResponseEntity.ok(restauranteActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		restauranteService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
