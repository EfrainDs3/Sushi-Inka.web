package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.AnulacionComprobante;

@Repository
public interface AnulacionComprobanteRepository extends JpaRepository<AnulacionComprobante, Integer> {
	
	List<AnulacionComprobante> findByIdVenta(Integer idVenta);

}
