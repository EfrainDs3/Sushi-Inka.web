package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.ComprobanteElectronico;

@Repository
public interface ComprobanteElectronicoRepository extends JpaRepository<ComprobanteElectronico, Integer> {
	
	List<ComprobanteElectronico> findByIdVenta(Integer idVenta);

}
