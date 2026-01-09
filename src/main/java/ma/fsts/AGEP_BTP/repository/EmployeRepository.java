package ma.fsts.agep_btp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.RoleEmploye;

import java.util.List;
import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findByDisponibleTrue();

    List<Employe> findByRoleAndDisponibleTrue(RoleEmploye role);

    Optional<Employe> findByEmail(String email);
}
