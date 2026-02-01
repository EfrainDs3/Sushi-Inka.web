package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.AnulacionComprobante;
import web.Sushi_Inka.repository.AnulacionComprobanteRepository;
import web.Sushi_Inka.service.IAnulacionComprobanteService;

@Service
public class AnulacionComprobanteServiceImpl implements IAnulacionComprobanteService {

	@Autowired
	private AnulacionComprobanteRepository anulacionComprobanteRepository;

	@Override
	public List<AnulacionComprobante> obtenerTodos() {
		return anulacionComprobanteRepository.findAll();
	}

	@Override
	public AnulacionComprobante obtenerPorId(Integer id) {
		return anulacionComprobanteRepository.findById(id).orElse(null);
	}

	@Override
	public AnulacionComprobante guardar(AnulacionComprobante anulacionComprobante) {
		return anulacionComprobanteRepository.save(anulacionComprobante);
	}

	@Override
	public AnulacionComprobante actualizar(Integer id, AnulacionComprobante anulacionComprobante) {
		AnulacionComprobante anulacionComprobanteExistente = anulacionComprobanteRepository.findById(id).orElse(null);
		if (anulacionComprobanteExistente != null) {
			anulacionComprobanteExistente.setIdVenta(anulacionComprobante.getIdVenta());
			anulacionComprobanteExistente.setMotivoAnulacion(anulacionComprobante.getMotivoAnulacion());
			anulacionComprobanteExistente.setUsuarioAnula(anulacionComprobante.getUsuarioAnula());
			anulacionComprobanteExistente.setFechaAnulacion(anulacionComprobante.getFechaAnulacion());
			anulacionComprobanteExistente.setEstado(anulacionComprobante.getEstado());
			return anulacionComprobanteRepository.save(anulacionComprobanteExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		anulacionComprobanteRepository.deleteById(id);
	}

	@Override
	public List<AnulacionComprobante> obtenerPorVenta(Integer idVenta) {
		return anulacionComprobanteRepository.findByIdVenta(idVenta);
	}

}
