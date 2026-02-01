package web.Sushi_Inka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.Sushi_Inka.entity.SuperAdmin;
import web.Sushi_Inka.repository.SuperAdminRepository;
import web.Sushi_Inka.service.ISuperAdminService;

@Service
public class SuperAdminServiceImpl implements ISuperAdminService {

	@Autowired
	private SuperAdminRepository superAdminRepository;

	@Override
	public SuperAdmin obtenerPorId(Integer id) {
		return superAdminRepository.findById(id).orElse(null);
	}

	@Override
	public SuperAdmin guardar(SuperAdmin superAdmin) {
		return superAdminRepository.save(superAdmin);
	}

	@Override
	public SuperAdmin actualizar(Integer id, SuperAdmin superAdmin) {
		SuperAdmin superAdminExistente = superAdminRepository.findById(id).orElse(null);
		if (superAdminExistente != null) {
			superAdminExistente.setUsuarioSuperAdmin(superAdmin.getUsuarioSuperAdmin());
			superAdminExistente.setContrasena(superAdmin.getContrasena());
			superAdminExistente.setEmail(superAdmin.getEmail());
			superAdminExistente.setNombreCompleto(superAdmin.getNombreCompleto());
			superAdminExistente.setUltimoLogin(superAdmin.getUltimoLogin());
			superAdminExistente.setEstado(superAdmin.getEstado());
			return superAdminRepository.save(superAdminExistente);
		}
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		superAdminRepository.deleteById(id);
	}

	@Override
	public SuperAdmin obtenerPorNombreUsuario(String usuarioSuperAdmin) {
		return superAdminRepository.findByUsuarioSuperAdmin(usuarioSuperAdmin);
	}

}
