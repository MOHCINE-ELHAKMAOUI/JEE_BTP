package ma.fsts.agep_btp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.ProjetMateriau;

import java.util.List;

public interface ProjetMateriauRepository extends JpaRepository<ProjetMateriau, Long> {

    List<ProjetMateriau> findByProjet(ProjetConstruction projet);
}
