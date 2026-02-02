package web.Sushi_Inka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {

}

