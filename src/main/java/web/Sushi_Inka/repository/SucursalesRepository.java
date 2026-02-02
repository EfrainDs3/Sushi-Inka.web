package web.Sushi_Inka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.Sucursal;

@Repository
public interface SucursalesRepository extends JpaRepository<Sucursal, Integer> {

}

