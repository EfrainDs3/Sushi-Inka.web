package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Mesa;
import web.Sushi_Inka.repository.MesaRepository;
import web.Sushi_Inka.service.IMesaService;

@Service
public class MesaService implements IMesaService {

	@Autowired
	private MesaRepository mesaRepository;

	@Override
	public List<Mesa> obtenerTodos() {
		return mesaRepository.findAll();
	}

	@Override
	public Mesa obtenerPorId(Integer id) {
		return mesaRepository.findById(id).orElse(null);
	}

	@Override
	public Mesa guardar(Mesa mesa) {
		return mesaRepository.save(mesa);
	}

	@Override
	public Mesa actualizar(Integer id, Mesa mesa) {
		Mesa mesaExistente = mesaRepository.findById(id).orElse(null);
		if (mesaExistente != null) {
			mesaExistente.setIdSucursal(mesa.getIdSucursal());
			mesaExistente.setNumeroMesa(mesa.getNumeroMesa());
			mesaExistente.setCapacidad(mesa.getCapacidad());
			mesaExistente.setUbicacion(mesa.getUbicacion());
			mesaExistente.setEstado(mesa.getEstado());
			return mesaRepository.save(mesaExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		mesaRepository.deleteById(id);
	}

	@Override
	public List<Mesa> obtenerPorSucursal(Integer idSucursal) {
		return mesaRepository.findByIdSucursal(idSucursal);
	}

}



