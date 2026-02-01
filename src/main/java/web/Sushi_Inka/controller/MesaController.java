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

import web.Sushi_Inka.entity.Mesa;
import web.Sushi_Inka.service.IMesaService;

@RestController
@RequestMapping("/restful/mesas")
public class MesaController {

	@Autowired
	private IMesaService mesaService;

	@GetMapping
	public ResponseEntity<List<Mesa>> obtenerTodos() {
		List<Mesa> mesas = mesaService.obtenerTodos();
		return ResponseEntity.ok(mesas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Mesa> obtenerPorId(@PathVariable Integer id) {
		Mesa mesa = mesaService.obtenerPorId(id);
		if (mesa != null) {
			return ResponseEntity.ok(mesa);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/sucursal/{idSucursal}")
	public ResponseEntity<List<Mesa>> obtenerPorSucursal(@PathVariable Integer idSucursal) {
		List<Mesa> mesas = mesaService.obtenerPorSucursal(idSucursal);
		return ResponseEntity.ok(mesas);
	}

	@PostMapping
	public ResponseEntity<Mesa> guardar(@RequestBody Mesa mesa) {
		Mesa nuevaMesa = mesaService.guardar(mesa);
		return ResponseEntity.ok(nuevaMesa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Mesa> actualizar(@PathVariable Integer id, @RequestBody Mesa mesa) {
		Mesa mesaActualizada = mesaService.actualizar(id, mesa);
		if (mesaActualizada != null) {
			return ResponseEntity.ok(mesaActualizada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		mesaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
