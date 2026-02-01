package web.Sushi_Inka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
	
	Usuarios findByNombreUsuarioLogin(String nombreUsuarioLogin);

}
