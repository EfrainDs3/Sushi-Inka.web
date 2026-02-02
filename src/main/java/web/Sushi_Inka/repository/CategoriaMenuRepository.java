package web.Sushi_Inka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.CategoriaMenu;

@Repository
public interface CategoriaMenuRepository extends JpaRepository<CategoriaMenu, Integer> {

}

