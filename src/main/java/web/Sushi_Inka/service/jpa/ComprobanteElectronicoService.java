package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.ComprobanteElectronico;
import web.Sushi_Inka.repository.ComprobanteElectronicoRepository;
import web.Sushi_Inka.service.IComprobanteElectronicoService;

@Service
public class ComprobanteElectronicoService implements IComprobanteElectronicoService {

	@Autowired
	private ComprobanteElectronicoRepository comprobanteElectronicoRepository;

	@Override
	public List<ComprobanteElectronico> obtenerTodos() {
		return comprobanteElectronicoRepository.findAll();
	}

	@Override
	public ComprobanteElectronico obtenerPorId(Integer id) {
		return comprobanteElectronicoRepository.findById(id).orElse(null);
	}

	@Override
	public ComprobanteElectronico guardar(ComprobanteElectronico comprobanteElectronico) {
		return comprobanteElectronicoRepository.save(comprobanteElectronico);
	}

	@Override
	public ComprobanteElectronico actualizar(Integer id, ComprobanteElectronico comprobanteElectronico) {
		ComprobanteElectronico comprobanteElectronicoExistente = comprobanteElectronicoRepository.findById(id).orElse(null);
		if (comprobanteElectronicoExistente != null) {
			comprobanteElectronicoExistente.setIdVenta(comprobanteElectronico.getIdVenta());
			comprobanteElectronicoExistente.setNumeroCdr(comprobanteElectronico.getNumeroCdr());
			comprobanteElectronicoExistente.setXmlPath(comprobanteElectronico.getXmlPath());
			comprobanteElectronicoExistente.setEstadoEnvio(comprobanteElectronico.getEstadoEnvio());
			comprobanteElectronicoExistente.setRespuestaSunat(comprobanteElectronico.getRespuestaSunat());
			comprobanteElectronicoExistente.setFechaEnvio(comprobanteElectronico.getFechaEnvio());
			comprobanteElectronicoExistente.setEstado(comprobanteElectronico.getEstado());
			return comprobanteElectronicoRepository.save(comprobanteElectronicoExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		comprobanteElectronicoRepository.deleteById(id);
	}

	@Override
	public List<ComprobanteElectronico> obtenerPorVenta(Integer idVenta) {
		return comprobanteElectronicoRepository.findByIdVenta(idVenta);
	}

}



