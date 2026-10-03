package ma.fsts.agep_btp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.ProjetConstruction;

import java.util.Optional;

public interface DevisRepository extends JpaRepository<Devis, Long> {

    Optional<Devis> findByProjet(ProjetConstruction projet);

    Optional<Devis> findByProjetId(Long projetId);

}
