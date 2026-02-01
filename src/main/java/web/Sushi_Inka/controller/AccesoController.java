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

import web.Sushi_Inka.entity.Acceso;
import web.Sushi_Inka.service.IAccesoService;

@RestController
@RequestMapping("/restful/accesos")
public class AccesoController {

	@Autowired
	private IAccesoService accesoService;

	@GetMapping
	public ResponseEntity<List<Acceso>> obtenerTodos() {
		List<Acceso> accesos = accesoService.obtenerTodos();
		return ResponseEntity.ok(accesos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Acceso> obtenerPorId(@PathVariable Integer id) {
		Acceso acceso = accesoService.obtenerPorId(id);
		if (acceso != null) {
			return ResponseEntity.ok(acceso);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/perfil/{idPerfil}")
	public ResponseEntity<List<Acceso>> obtenerAccesosPorPerfil(@PathVariable Integer idPerfil) {
		List<Acceso> accesos = accesoService.obtenerAccesosPorPerfil(idPerfil);
		return ResponseEntity.ok(accesos);
	}

	@PostMapping
	public ResponseEntity<Acceso> guardar(@RequestBody Acceso acceso) {
		Acceso nuevoAcceso = accesoService.guardar(acceso);
		return ResponseEntity.ok(nuevoAcceso);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Acceso> actualizar(@PathVariable Integer id, @RequestBody Acceso acceso) {
		Acceso accesoActualizado = accesoService.actualizar(id, acceso);
		if (accesoActualizado != null) {
			return ResponseEntity.ok(accesoActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		accesoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
