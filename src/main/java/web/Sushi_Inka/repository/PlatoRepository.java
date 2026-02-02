package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Plato;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {
	
	List<Plato> findByIdCategoria(Integer idCategoria);
	List<Plato> findByIdSucursal(Integer idSucursal);

}

