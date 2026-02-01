package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.PagoSuscripcion;
import web.Sushi_Inka.repository.PagoSuscripcionRepository;
import web.Sushi_Inka.service.IPagoSuscripcionService;

@Service
public class PagoSuscripcionServiceImpl implements IPagoSuscripcionService {

	@Autowired
	private PagoSuscripcionRepository pagoSuscripcionRepository;

	@Override
	public List<PagoSuscripcion> obtenerTodos() {
		return pagoSuscripcionRepository.findAll();
	}

	@Override
	public PagoSuscripcion obtenerPorId(Integer id) {
		return pagoSuscripcionRepository.findById(id).orElse(null);
	}

	@Override
	public PagoSuscripcion guardar(PagoSuscripcion pagoSuscripcion) {
		return pagoSuscripcionRepository.save(pagoSuscripcion);
	}

	@Override
	public PagoSuscripcion actualizar(Integer id, PagoSuscripcion pagoSuscripcion) {
		PagoSuscripcion pagoSuscripcionExistente = pagoSuscripcionRepository.findById(id).orElse(null);
		if (pagoSuscripcionExistente != null) {
			pagoSuscripcionExistente.setIdRestaurante(pagoSuscripcion.getIdRestaurante());
			pagoSuscripcionExistente.setMontoPago(pagoSuscripcion.getMontoPago());
			pagoSuscripcionExistente.setTipoSuscripcion(pagoSuscripcion.getTipoSuscripcion());
			pagoSuscripcionExistente.setFechaPago(pagoSuscripcion.getFechaPago());
			pagoSuscripcionExistente.setFechaVencimiento(pagoSuscripcion.getFechaVencimiento());
			pagoSuscripcionExistente.setNumeroReferencia(pagoSuscripcion.getNumeroReferencia());
			pagoSuscripcionExistente.setEstado(pagoSuscripcion.getEstado());
			return pagoSuscripcionRepository.save(pagoSuscripcionExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		pagoSuscripcionRepository.deleteById(id);
	}

	@Override
	public List<PagoSuscripcion> obtenerPorRestaurante(Integer idRestaurante) {
		return pagoSuscripcionRepository.findByIdRestaurante(idRestaurante);
	}

}
