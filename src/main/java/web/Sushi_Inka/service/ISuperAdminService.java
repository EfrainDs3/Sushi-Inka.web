package web.Sushi_Inka.service;

import web.Sushi_Inka.entity.SuperAdmin;

public interface ISuperAdminService {
	
	SuperAdmin obtenerPorId(Integer id);
	SuperAdmin guardar(SuperAdmin superAdmin);
	SuperAdmin actualizar(Integer id, SuperAdmin superAdmin);
	void eliminar(Integer id);
	SuperAdmin obtenerPorNombreUsuario(String usuarioSuperAdmin);

}
