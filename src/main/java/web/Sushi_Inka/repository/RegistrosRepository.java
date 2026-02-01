package web.Sushi_Inka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Registros;

@Repository
public interface RegistrosRepository extends JpaRepository<Registros, Integer> {
	
	Registros findByToken(String token);
	List<Registros> findByEmail(String email);

}
