package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer> {
	
	List<Insumo> findByIdSucursal(Integer idSucursal);

}

