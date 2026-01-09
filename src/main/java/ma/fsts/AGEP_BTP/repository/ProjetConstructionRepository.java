package ma.fsts.agep_btp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.StatutProjet;

import java.util.List;

public interface ProjetConstructionRepository extends JpaRepository<ProjetConstruction, Long> {

    List<ProjetConstruction> findByStatut(StatutProjet statut);

    List<ProjetConstruction> findByTypeConstruction(String typeConstruction);
}

