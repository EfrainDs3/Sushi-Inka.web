package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
	
	List<Sucursal> findByIdRestaurante(Integer idRestaurante);

}

