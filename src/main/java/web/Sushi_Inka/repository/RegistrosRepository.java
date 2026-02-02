package web.Sushi_Inka.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Registros;

@Repository
public interface RegistrosRepository extends JpaRepository<Registros, Integer> {

	Optional<Registros> findByEmail(String email);

	@Query("SELECT r FROM Registros r WHERE r.access_token = :accessToken")
	Optional<Registros> findByAccessToken(@Param("accessToken") String accessToken);

}

