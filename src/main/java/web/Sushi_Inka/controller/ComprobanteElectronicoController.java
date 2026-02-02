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

import web.Sushi_Inka.entity.ComprobanteElectronico;
import web.Sushi_Inka.service.IComprobanteElectronicoService;

@RestController
@RequestMapping("/restful/comprobantes-electronicos")
public class ComprobanteElectronicoController {

	@Autowired
	private IComprobanteElectronicoService comprobanteElectronicoService;

	@GetMapping
	public ResponseEntity<List<ComprobanteElectronico>> obtenerTodos() {
		List<ComprobanteElectronico> comprobantes = comprobanteElectronicoService.obtenerTodos();
		return ResponseEntity.ok(comprobantes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComprobanteElectronico> obtenerPorId(@PathVariable Integer id) {
		ComprobanteElectronico comprobante = comprobanteElectronicoService.obtenerPorId(id);
		if (comprobante != null) {
			return ResponseEntity.ok(comprobante);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/venta/{idVenta}")
	public ResponseEntity<List<ComprobanteElectronico>> obtenerPorVenta(@PathVariable Integer idVenta) {
		List<ComprobanteElectronico> comprobantes = comprobanteElectronicoService.obtenerPorVenta(idVenta);
		return ResponseEntity.ok(comprobantes);
	}

	@PostMapping
	public ResponseEntity<ComprobanteElectronico> guardar(@RequestBody ComprobanteElectronico comprobanteElectronico) {
		ComprobanteElectronico nuevoComprobante = comprobanteElectronicoService.guardar(comprobanteElectronico);
		return ResponseEntity.ok(nuevoComprobante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComprobanteElectronico> actualizar(@PathVariable Integer id, @RequestBody ComprobanteElectronico comprobanteElectronico) {
		ComprobanteElectronico comprobanteActualizado = comprobanteElectronicoService.actualizar(id, comprobanteElectronico);
		if (comprobanteActualizado != null) {
			return ResponseEntity.ok(comprobanteActualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		comprobanteElectronicoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}

