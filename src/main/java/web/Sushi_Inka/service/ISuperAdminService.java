package web.Sushi_Inka.service;

import java.util.List;
import web.Sushi_Inka.entity.SuperAdmin;

public interface ISuperAdminService {

	List<SuperAdmin> obtenerTodos();

	SuperAdmin obtenerPorId(Integer id);

	SuperAdmin guardar(SuperAdmin superAdmin);

	SuperAdmin actualizar(Integer id, SuperAdmin superAdmin);

	void eliminar(Integer id);

	SuperAdmin obtenerPorNombreUsuario(String usuarioSuperAdmin);

}
