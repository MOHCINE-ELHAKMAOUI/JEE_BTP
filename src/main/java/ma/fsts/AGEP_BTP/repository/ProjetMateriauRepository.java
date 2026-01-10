package ma.fsts.agep_btp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.ProjetMateriau;

import java.util.List;

public interface ProjetMateriauRepository extends JpaRepository<ProjetMateriau, Long> {

    List<ProjetMateriau> findByProjet(ProjetConstruction projet);
    
    @Query("SELECT pm FROM ProjetMateriau pm JOIN FETCH pm.materiau WHERE pm.projet.id = :projetId")
    List<ProjetMateriau> findByProjetIdWithMateriau(@Param("projetId") Long projetId);
}
