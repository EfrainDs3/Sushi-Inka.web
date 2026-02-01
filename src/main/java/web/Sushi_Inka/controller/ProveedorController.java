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

import web.Sushi_Inka.entity.Proveedor;
import web.Sushi_Inka.service.IProveedorService;

@RestController
@RequestMapping("/restful/proveedores")
public class ProveedorController {

	@Autowired
	private IProveedorService proveedorService;

	@GetMapping
	public ResponseEntity<List<Proveedor>> obtenerTodos() {
		List<Proveedor> proveedores = proveedorService.obtenerTodos();
		return ResponseEntity.ok(proveedores);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Integer id) {
		Proveedor proveedor = proveedorService.obtenerPorId(id);
		if (proveedor != null) {
			return ResponseEntity.ok(proveedor);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/sucursal/{idSucursal}")
	public ResponseEntity<List<Proveedor>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
		List<Proveedor> proveedores = proveedorService.obtenerPorSucursal(idSucursal);
		return ResponseEntity.ok(proveedores);
	}

	@PostMapping
	public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor proveedor) {
		Proveedor nuevoProveedor = proveedorService.guardar(proveedor);
		return ResponseEntity.ok(nuevoProveedor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Proveedor> actualizar(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
		Proveedor proveedorActualizado = proveedorService.actualizar(id, proveedor);
		if (proveedorActualizado != null) {
			return ResponseEntity.ok(proveedorActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		proveedorService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
