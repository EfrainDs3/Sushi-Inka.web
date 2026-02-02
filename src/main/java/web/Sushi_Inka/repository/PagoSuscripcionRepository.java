package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.PagoSuscripcion;

@Repository
public interface PagoSuscripcionRepository extends JpaRepository<PagoSuscripcion, Integer> {
	
	List<PagoSuscripcion> findByIdRestaurante(Integer idRestaurante);

}

