package ma.fsts.AGEP_BTP.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.AGEP_BTP.entity.ProjetConstruction;
import ma.fsts.AGEP_BTP.entity.StatutProjet;

import java.util.List;

public interface ProjetConstructionRepository extends JpaRepository<ProjetConstruction, Long> {

    List<ProjetConstruction> findByStatut(StatutProjet statut);

    List<ProjetConstruction> findByTypeConstruction(String typeConstruction);
}

