package web.Sushi_Inka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

}
