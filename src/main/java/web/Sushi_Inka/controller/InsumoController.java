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

import web.Sushi_Inka.entity.Insumo;
import web.Sushi_Inka.service.IInsumoService;

@RestController
@RequestMapping("/restful/insumos")
public class InsumoController {

	@Autowired
	private IInsumoService insumoService;

	@GetMapping
	public ResponseEntity<List<Insumo>> obtenerTodos() {
		List<Insumo> insumos = insumoService.obtenerTodos();
		return ResponseEntity.ok(insumos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Insumo> obtenerPorId(@PathVariable Integer id) {
		Insumo insumo = insumoService.obtenerPorId(id);
		if (insumo != null) {
			return ResponseEntity.ok(insumo);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/sucursal/{idSucursal}")
	public ResponseEntity<List<Insumo>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
		List<Insumo> insumos = insumoService.obtenerPorSucursal(idSucursal);
		return ResponseEntity.ok(insumos);
	}

	@PostMapping
	public ResponseEntity<Insumo> guardar(@RequestBody Insumo insumo) {
		Insumo nuevoInsumo = insumoService.guardar(insumo);
		return ResponseEntity.ok(nuevoInsumo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Insumo> actualizar(@PathVariable Integer id, @RequestBody Insumo insumo) {
		Insumo insumoActualizado = insumoService.actualizar(id, insumo);
		if (insumoActualizado != null) {
			return ResponseEntity.ok(insumoActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		insumoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
