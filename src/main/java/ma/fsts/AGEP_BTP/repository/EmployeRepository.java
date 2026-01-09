package ma.fsts.AGEP_BTP.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.AGEP_BTP.entity.Employe;
import ma.fsts.AGEP_BTP.entity.RoleEmploye;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findByDisponibleTrue();

    List<Employe> findByRoleAndDisponibleTrue(RoleEmploye role);
}
