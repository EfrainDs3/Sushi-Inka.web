package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.MovimientoInventario;
import web.Sushi_Inka.repository.MovimientoInventarioRepository;
import web.Sushi_Inka.service.IMovimientoInventarioService;

@Service
public class MovimientoInventarioService implements IMovimientoInventarioService {

	@Autowired
	private MovimientoInventarioRepository movimientoInventarioRepository;

	@Override
	public List<MovimientoInventario> obtenerTodos() {
		return movimientoInventarioRepository.findAll();
	}

	@Override
	public MovimientoInventario obtenerPorId(Integer id) {
		return movimientoInventarioRepository.findById(id).orElse(null);
	}

	@Override
	public MovimientoInventario guardar(MovimientoInventario movimientoInventario) {
		return movimientoInventarioRepository.save(movimientoInventario);
	}

	@Override
	public MovimientoInventario actualizar(Integer id, MovimientoInventario movimientoInventario) {
		MovimientoInventario movimientoInventarioExistente = movimientoInventarioRepository.findById(id).orElse(null);
		if (movimientoInventarioExistente != null) {
			movimientoInventarioExistente.setIdInsumo(movimientoInventario.getIdInsumo());
			movimientoInventarioExistente.setTipoMovimiento(movimientoInventario.getTipoMovimiento());
			movimientoInventarioExistente.setCantidad(movimientoInventario.getCantidad());
			movimientoInventarioExistente.setDescripcion(movimientoInventario.getDescripcion());
			movimientoInventarioExistente.setFechaHora(movimientoInventario.getFechaHora());
			movimientoInventarioExistente.setEstado(movimientoInventario.getEstado());
			return movimientoInventarioRepository.save(movimientoInventarioExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		movimientoInventarioRepository.deleteById(id);
	}

	@Override
	public List<MovimientoInventario> obtenerPorInsumo(Integer idInsumo) {
		return movimientoInventarioRepository.findByIdInsumo(idInsumo);
	}

}



