package web.Sushi_Inka.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.Acceso;
import web.Sushi_Inka.repository.AccesoRepository;
import web.Sushi_Inka.service.IAccesoService;

@Service
public class AccesoService implements IAccesoService {

	@Autowired
	private AccesoRepository accesoRepository;

	@Override
	public List<Acceso> obtenerTodos() {
		return accesoRepository.findAll();
	}

	@Override
	public Acceso obtenerPorId(Integer id) {
		return accesoRepository.findById(id).orElse(null);
	}

	@Override
	public Acceso guardar(Acceso acceso) {
		return accesoRepository.save(acceso);
	}

	@Override
	public Acceso actualizar(Integer id, Acceso acceso) {
		Acceso accesoExistente = accesoRepository.findById(id).orElse(null);
		if (accesoExistente != null) {
			accesoExistente.setIdModulo(acceso.getIdModulo());
			accesoExistente.setIdPerfil(acceso.getIdPerfil());
			accesoExistente.setPuedeVer(acceso.getPuedeVer());
			accesoExistente.setPuedeCrear(acceso.getPuedeCrear());
			accesoExistente.setPuedeEditar(acceso.getPuedeEditar());
			accesoExistente.setPuedeEliminar(acceso.getPuedeEliminar());
			accesoExistente.setEstado(acceso.getEstado());
			return accesoRepository.save(accesoExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		accesoRepository.deleteById(id);
	}

	@Override
	public List<Acceso> obtenerAccesosPorPerfil(Integer idPerfil) {
		return accesoRepository.findByIdPerfil(idPerfil);
	}

}



