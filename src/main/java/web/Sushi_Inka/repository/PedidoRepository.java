package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByIdSucursal(Integer idSucursal);
	List<Pedido> findByIdMesa(Integer idMesa);

}

