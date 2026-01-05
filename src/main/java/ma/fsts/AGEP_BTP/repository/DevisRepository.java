package ma.fsts.AGEP_BTP.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.AGEP_BTP.entity.Devis;
import ma.fsts.AGEP_BTP.entity.ProjetConstruction;

import java.util.Optional;

public interface DevisRepository extends JpaRepository<Devis, Long> {

    Optional<Devis> findByProjet(ProjetConstruction projet);
}
