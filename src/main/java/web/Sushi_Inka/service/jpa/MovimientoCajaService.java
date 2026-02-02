package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.MovimientoCaja;
import web.Sushi_Inka.repository.MovimientoCajaRepository;
import web.Sushi_Inka.service.IMovimientoCajaService;

@Service
public class MovimientoCajaService implements IMovimientoCajaService {

	@Autowired
	private MovimientoCajaRepository movimientoCajaRepository;

	@Override
	public List<MovimientoCaja> obtenerTodos() {
		return movimientoCajaRepository.findAll();
	}

	@Override
	public MovimientoCaja obtenerPorId(Integer id) {
		return movimientoCajaRepository.findById(id).orElse(null);
	}

	@Override
	public MovimientoCaja guardar(MovimientoCaja movimientoCaja) {
		return movimientoCajaRepository.save(movimientoCaja);
	}

	@Override
	public MovimientoCaja actualizar(Integer id, MovimientoCaja movimientoCaja) {
		MovimientoCaja movimientoCajaExistente = movimientoCajaRepository.findById(id).orElse(null);
		if (movimientoCajaExistente != null) {
			movimientoCajaExistente.setIdSesionCaja(movimientoCaja.getIdSesionCaja());
			movimientoCajaExistente.setTipoMovimiento(movimientoCaja.getTipoMovimiento());
			movimientoCajaExistente.setMonto(movimientoCaja.getMonto());
			movimientoCajaExistente.setDescripcion(movimientoCaja.getDescripcion());
			movimientoCajaExistente.setFechaHora(movimientoCaja.getFechaHora());
			movimientoCajaExistente.setEstado(movimientoCaja.getEstado());
			return movimientoCajaRepository.save(movimientoCajaExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		movimientoCajaRepository.deleteById(id);
	}

	@Override
	public List<MovimientoCaja> obtenerPorSesionCaja(Integer idSesionCaja) {
		return movimientoCajaRepository.findByIdSesionCaja(idSesionCaja);
	}

}



