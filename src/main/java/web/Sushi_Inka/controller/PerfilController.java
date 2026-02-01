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

import web.Sushi_Inka.entity.Perfil;
import web.Sushi_Inka.service.IPerfilService;

@RestController
@RequestMapping("/restful/perfiles")
public class PerfilController {

	@Autowired
	private IPerfilService perfilService;

	@GetMapping
	public ResponseEntity<List<Perfil>> obtenerTodos() {
		List<Perfil> perfiles = perfilService.obtenerTodos();
		return ResponseEntity.ok(perfiles);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Perfil> obtenerPorId(@PathVariable Integer id) {
		Perfil perfil = perfilService.obtenerPorId(id);
		if (perfil != null) {
			return ResponseEntity.ok(perfil);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Perfil> guardar(@RequestBody Perfil perfil) {
		Perfil nuevoPerfil = perfilService.guardar(perfil);
		return ResponseEntity.ok(nuevoPerfil);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Perfil> actualizar(@PathVariable Integer id, @RequestBody Perfil perfil) {
		Perfil perfilActualizado = perfilService.actualizar(id, perfil);
		if (perfilActualizado != null) {
			return ResponseEntity.ok(perfilActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		perfilService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
