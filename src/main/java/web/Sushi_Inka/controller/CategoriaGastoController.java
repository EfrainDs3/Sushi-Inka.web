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

import web.Sushi_Inka.entity.CategoriaGasto;
import web.Sushi_Inka.service.ICategoriaGastoService;

@RestController
@RequestMapping("/restful/categorias-gasto")
public class CategoriaGastoController {

	@Autowired
	private ICategoriaGastoService categoriaGastoService;

	@GetMapping
	public ResponseEntity<List<CategoriaGasto>> obtenerTodos() {
		List<CategoriaGasto> categorias = categoriaGastoService.obtenerTodos();
		return ResponseEntity.ok(categorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaGasto> obtenerPorId(@PathVariable Integer id) {
		CategoriaGasto categoria = categoriaGastoService.obtenerPorId(id);
		if (categoria != null) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<CategoriaGasto> guardar(@RequestBody CategoriaGasto categoriaGasto) {
		CategoriaGasto nuevaCategoria = categoriaGastoService.guardar(categoriaGasto);
		return ResponseEntity.ok(nuevaCategoria);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaGasto> actualizar(@PathVariable Integer id, @RequestBody CategoriaGasto categoriaGasto) {
		CategoriaGasto categoriaActualizada = categoriaGastoService.actualizar(id, categoriaGasto);
		if (categoriaActualizada != null) {
			return ResponseEntity.ok(categoriaActualizada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		categoriaGastoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

