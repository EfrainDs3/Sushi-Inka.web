package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
	
	List<Venta> findByIdSucursal(Integer idSucursal);
	List<Venta> findByIdPedido(Integer idPedido);

}
