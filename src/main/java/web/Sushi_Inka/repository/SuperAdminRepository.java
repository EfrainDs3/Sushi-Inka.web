package web.Sushi_Inka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.Sushi_Inka.entity.SuperAdmin;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer> {

	java.util.Optional<SuperAdmin> findByUsuarioSuperAdmin(String usuarioSuperAdmin);

	java.util.Optional<SuperAdmin> findByEmail(String email);

}
