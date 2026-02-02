package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
	
	List<Mesa> findByIdSucursal(Integer idSucursal);

}

