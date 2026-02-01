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

import web.Sushi_Inka.entity.AnulacionComprobante;
import web.Sushi_Inka.service.IAnulacionComprobanteService;

@RestController
@RequestMapping("/restful/anulaciones-comprobante")
public class AnulacionComprobanteController {

	@Autowired
	private IAnulacionComprobanteService anulacionComprobanteService;

	@GetMapping
	public ResponseEntity<List<AnulacionComprobante>> obtenerTodos() {
		List<AnulacionComprobante> anulaciones = anulacionComprobanteService.obtenerTodos();
		return ResponseEntity.ok(anulaciones);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AnulacionComprobante> obtenerPorId(@PathVariable Integer id) {
		AnulacionComprobante anulacion = anulacionComprobanteService.obtenerPorId(id);
		if (anulacion != null) {
			return ResponseEntity.ok(anulacion);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/venta/{idVenta}")
	public ResponseEntity<List<AnulacionComprobante>> obtenerPorVenta(@PathVariable Integer idVenta) {
		List<AnulacionComprobante> anulaciones = anulacionComprobanteService.obtenerPorVenta(idVenta);
		return ResponseEntity.ok(anulaciones);
	}

	@PostMapping
	public ResponseEntity<AnulacionComprobante> guardar(@RequestBody AnulacionComprobante anulacionComprobante) {
		AnulacionComprobante nuevaAnulacion = anulacionComprobanteService.guardar(anulacionComprobante);
		return ResponseEntity.ok(nuevaAnulacion);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AnulacionComprobante> actualizar(@PathVariable Integer id, @RequestBody AnulacionComprobante anulacionComprobante) {
		AnulacionComprobante anulacionActualizada = anulacionComprobanteService.actualizar(id, anulacionComprobante);
		if (anulacionActualizada != null) {
			return ResponseEntity.ok(anulacionActualizada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		anulacionComprobanteService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
