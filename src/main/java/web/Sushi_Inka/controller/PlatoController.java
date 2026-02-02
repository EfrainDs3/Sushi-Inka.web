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

import web.Sushi_Inka.entity.Plato;
import web.Sushi_Inka.service.IPlatoService;

@RestController
@RequestMapping("/restful/platos")
public class PlatoController {

	@Autowired
	private IPlatoService platoService;

	@GetMapping
	public ResponseEntity<List<Plato>> obtenerTodos() {
		List<Plato> platos = platoService.obtenerTodos();
		return ResponseEntity.ok(platos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Plato> obtenerPorId(@PathVariable Integer id) {
		Plato plato = platoService.obtenerPorId(id);
		if (plato != null) {
			return ResponseEntity.ok(plato);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/categoria/{idCategoria}")
	public ResponseEntity<List<Plato>> obtenerPorCategoria(@PathVariable Integer idCategoria) {
		List<Plato> platos = platoService.obtenerPorCategoria(idCategoria);
		return ResponseEntity.ok(platos);
	}

	@GetMapping("/sucursal/{idSucursal}")
	public ResponseEntity<List<Plato>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
		List<Plato> platos = platoService.obtenerPorSucursal(idSucursal);
		return ResponseEntity.ok(platos);
	}

	@PostMapping
	public ResponseEntity<Plato> guardar(@RequestBody Plato plato) {
		Plato nuevoPlato = platoService.guardar(plato);
		return ResponseEntity.ok(nuevoPlato);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Plato> actualizar(@PathVariable Integer id, @RequestBody Plato plato) {
		Plato platoActualizado = platoService.actualizar(id, plato);
		if (platoActualizado != null) {
			return ResponseEntity.ok(platoActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		platoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

