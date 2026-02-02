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

import web.Sushi_Inka.entity.SuperAdmin;
import web.Sushi_Inka.service.ISuperAdminService;

@RestController
@RequestMapping("/restful/super-admins")
public class SuperAdminController {

	@Autowired
	private ISuperAdminService superAdminService;

	@GetMapping
	public ResponseEntity<List<SuperAdmin>> obtenerTodos() {
		List<SuperAdmin> superAdmins = superAdminService.obtenerTodos();
		return ResponseEntity.ok(superAdmins);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuperAdmin> obtenerPorId(@PathVariable Integer id) {
		SuperAdmin superAdmin = superAdminService.obtenerPorId(id);
		if (superAdmin != null) {
			return ResponseEntity.ok(superAdmin);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/usuario/{nombreUsuario}")
	public ResponseEntity<SuperAdmin> obtenerPorNombreUsuario(@PathVariable String nombreUsuario) {
		SuperAdmin superAdmin = superAdminService.obtenerPorNombreUsuario(nombreUsuario);
		return ResponseEntity.ok(superAdmin);
	}

	@PostMapping
	public ResponseEntity<SuperAdmin> guardar(@RequestBody SuperAdmin superAdmin) {
		SuperAdmin nuevoSuperAdmin = superAdminService.guardar(superAdmin);
		return ResponseEntity.ok(nuevoSuperAdmin);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuperAdmin> actualizar(@PathVariable Integer id, @RequestBody SuperAdmin superAdmin) {
		SuperAdmin superAdminActualizado = superAdminService.actualizar(id, superAdmin);
		if (superAdminActualizado != null) {
			return ResponseEntity.ok(superAdminActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		superAdminService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

