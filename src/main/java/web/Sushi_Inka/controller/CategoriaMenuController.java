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

import web.Sushi_Inka.entity.CategoriaMenu;
import web.Sushi_Inka.service.ICategoriaMenuService;

@RestController
@RequestMapping("/restful/categorias-menu")
public class CategoriaMenuController {

	@Autowired
	private ICategoriaMenuService categoriaMenuService;

	@GetMapping
	public ResponseEntity<List<CategoriaMenu>> obtenerTodos() {
		List<CategoriaMenu> categorias = categoriaMenuService.obtenerTodos();
		return ResponseEntity.ok(categorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaMenu> obtenerPorId(@PathVariable Integer id) {
		CategoriaMenu categoria = categoriaMenuService.obtenerPorId(id);
		if (categoria != null) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<CategoriaMenu> guardar(@RequestBody CategoriaMenu categoriaMenu) {
		CategoriaMenu nuevaCategoria = categoriaMenuService.guardar(categoriaMenu);
		return ResponseEntity.ok(nuevaCategoria);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaMenu> actualizar(@PathVariable Integer id, @RequestBody CategoriaMenu categoriaMenu) {
		CategoriaMenu categoriaActualizada = categoriaMenuService.actualizar(id, categoriaMenu);
		if (categoriaActualizada != null) {
			return ResponseEntity.ok(categoriaActualizada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		categoriaMenuService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
