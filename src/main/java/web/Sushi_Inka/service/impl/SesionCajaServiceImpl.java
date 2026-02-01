package web.Sushi_Inka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.SesionCaja;
import web.Sushi_Inka.repository.SesionCajaRepository;
import web.Sushi_Inka.service.ISesionCajaService;

@Service
public class SesionCajaServiceImpl implements ISesionCajaService {

	@Autowired
	private SesionCajaRepository sesionCajaRepository;

	@Override
	public List<SesionCaja> obtenerTodos() {
		return sesionCajaRepository.findAll();
	}

	@Override
	public SesionCaja obtenerPorId(Integer id) {
		return sesionCajaRepository.findById(id).orElse(null);
	}

	@Override
	public SesionCaja guardar(SesionCaja sesionCaja) {
		return sesionCajaRepository.save(sesionCaja);
	}

	@Override
	public SesionCaja actualizar(Integer id, SesionCaja sesionCaja) {
		SesionCaja sesionCajaExistente = sesionCajaRepository.findById(id).orElse(null);
		if (sesionCajaExistente != null) {
			sesionCajaExistente.setIdSucursal(sesionCaja.getIdSucursal());
			sesionCajaExistente.setIdUsuario(sesionCaja.getIdUsuario());
			sesionCajaExistente.setSaldoInicial(sesionCaja.getSaldoInicial());
			sesionCajaExistente.setSaldoFinal(sesionCaja.getSaldoFinal());
			sesionCajaExistente.setTotalMovimientos(sesionCaja.getTotalMovimientos());
			sesionCajaExistente.setFechaApertura(sesionCaja.getFechaApertura());
			sesionCajaExistente.setFechaCierre(sesionCaja.getFechaCierre());
			sesionCajaExistente.setEstado(sesionCaja.getEstado());
			return sesionCajaRepository.save(sesionCajaExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		sesionCajaRepository.deleteById(id);
	}

	@Override
	public List<SesionCaja> obtenerPorSucursal(Integer idSucursal) {
		return sesionCajaRepository.findByIdSucursal(idSucursal);
	}

}
