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

import web.Sushi_Inka.entity.Sucursal;
import web.Sushi_Inka.service.ISucursalService;

@RestController
@RequestMapping("/restful/sucursales")
public class SucursalController {

	@Autowired
	private ISucursalService sucursalService;

	@GetMapping
	public ResponseEntity<List<Sucursal>> obtenerTodos() {
		List<Sucursal> sucursales = sucursalService.obtenerTodos();
		return ResponseEntity.ok(sucursales);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sucursal> obtenerPorId(@PathVariable Integer id) {
		Sucursal sucursal = sucursalService.obtenerPorId(id);
		if (sucursal != null) {
			return ResponseEntity.ok(sucursal);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/restaurante/{idRestaurante}")
	public ResponseEntity<List<Sucursal>> obtenerPorRestaurante(@PathVariable Integer idRestaurante) {
		List<Sucursal> sucursales = sucursalService.obtenerPorRestaurante(idRestaurante);
		return ResponseEntity.ok(sucursales);
	}

	@PostMapping
	public ResponseEntity<Sucursal> guardar(@RequestBody Sucursal sucursal) {
		Sucursal nuevaSucursal = sucursalService.guardar(sucursal);
		return ResponseEntity.ok(nuevaSucursal);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Sucursal> actualizar(@PathVariable Integer id, @RequestBody Sucursal sucursal) {
		Sucursal sucursalActualizada = sucursalService.actualizar(id, sucursal);
		if (sucursalActualizada != null) {
			return ResponseEntity.ok(sucursalActualizada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		sucursalService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

