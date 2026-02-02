package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.BitacoraAccion;

@Repository
public interface BitacoraAccionRepository extends JpaRepository<BitacoraAccion, Integer> {
	
	List<BitacoraAccion> findByIdUsuario(Integer idUsuario);

}

