package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.MovimientoCaja;

@Repository
public interface MovimientoCajaRepository extends JpaRepository<MovimientoCaja, Integer> {
	
	List<MovimientoCaja> findByIdSesionCaja(Integer idSesionCaja);

}
