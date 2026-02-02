package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.BitacoraAccion;
import web.Sushi_Inka.repository.BitacoraAccionRepository;
import web.Sushi_Inka.service.IBitacoraAccionService;

@Service
public class BitacoraAccionService implements IBitacoraAccionService {

	@Autowired
	private BitacoraAccionRepository bitacoraAccionRepository;

	@Override
	public List<BitacoraAccion> obtenerTodos() {
		return bitacoraAccionRepository.findAll();
	}

	@Override
	public BitacoraAccion obtenerPorId(Integer id) {
		return bitacoraAccionRepository.findById(id).orElse(null);
	}

	@Override
	public BitacoraAccion guardar(BitacoraAccion bitacoraAccion) {
		return bitacoraAccionRepository.save(bitacoraAccion);
	}

	@Override
	public BitacoraAccion actualizar(Integer id, BitacoraAccion bitacoraAccion) {
		BitacoraAccion bitacoraAccionExistente = bitacoraAccionRepository.findById(id).orElse(null);
		if (bitacoraAccionExistente != null) {
			bitacoraAccionExistente.setIdUsuario(bitacoraAccion.getIdUsuario());
			bitacoraAccionExistente.setModulo(bitacoraAccion.getModulo());
			bitacoraAccionExistente.setAccion(bitacoraAccion.getAccion());
			bitacoraAccionExistente.setDescripcion(bitacoraAccion.getDescripcion());
			bitacoraAccionExistente.setIpOrigen(bitacoraAccion.getIpOrigen());
			bitacoraAccionExistente.setFechaHora(bitacoraAccion.getFechaHora());
			bitacoraAccionExistente.setEstado(bitacoraAccion.getEstado());
			return bitacoraAccionRepository.save(bitacoraAccionExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		bitacoraAccionRepository.deleteById(id);
	}

	@Override
	public List<BitacoraAccion> obtenerPorUsuario(Integer idUsuario) {
		return bitacoraAccionRepository.findByIdUsuario(idUsuario);
	}

}



