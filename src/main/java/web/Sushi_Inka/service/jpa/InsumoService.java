package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Insumo;
import web.Sushi_Inka.repository.InsumoRepository;
import web.Sushi_Inka.service.IInsumoService;

@Service
public class InsumoService implements IInsumoService {

	@Autowired
	private InsumoRepository insumoRepository;

	@Override
	public List<Insumo> obtenerTodos() {
		return insumoRepository.findAll();
	}

	@Override
	public Insumo obtenerPorId(Integer id) {
		return insumoRepository.findById(id).orElse(null);
	}

	@Override
	public Insumo guardar(Insumo insumo) {
		return insumoRepository.save(insumo);
	}

	@Override
	public Insumo actualizar(Integer id, Insumo insumo) {
		Insumo insumoExistente = insumoRepository.findById(id).orElse(null);
		if (insumoExistente != null) {
			insumoExistente.setIdSucursal(insumo.getIdSucursal());
			insumoExistente.setNombre(insumo.getNombre());
			insumoExistente.setStockActual(insumo.getStockActual());
			insumoExistente.setStockMinimo(insumo.getStockMinimo());
			insumoExistente.setUnidadMedida(insumo.getUnidadMedida());
			insumoExistente.setPrecioUnitario(insumo.getPrecioUnitario());
			insumoExistente.setFechaVencimiento(insumo.getFechaVencimiento());
			insumoExistente.setEstado(insumo.getEstado());
			return insumoRepository.save(insumoExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		insumoRepository.deleteById(id);
	}

	@Override
	public List<Insumo> obtenerPorSucursal(Integer idSucursal) {
		return insumoRepository.findByIdSucursal(idSucursal);
	}

}



