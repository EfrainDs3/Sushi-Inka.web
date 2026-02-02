package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Acceso;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, Integer> {
	
	List<Acceso> findByIdPerfil(Integer idPerfil);

}

