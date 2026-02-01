package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.SesionCaja;

@Repository
public interface SesionCajaRepository extends JpaRepository<SesionCaja, Integer> {
	
	List<SesionCaja> findByIdSucursal(Integer idSucursal);

}
