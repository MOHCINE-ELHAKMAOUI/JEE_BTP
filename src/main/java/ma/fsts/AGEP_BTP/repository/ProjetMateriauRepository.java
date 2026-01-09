package ma.fsts.AGEP_BTP.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.AGEP_BTP.entity.ProjetConstruction;
import ma.fsts.AGEP_BTP.entity.ProjetMateriau;

import java.util.List;

public interface ProjetMateriauRepository extends JpaRepository<ProjetMateriau, Long> {

    List<ProjetMateriau> findByProjet(ProjetConstruction projet);
}
