package ma.fsts.agep_btp.service;

import java.util.List;

import ma.fsts.agep_btp.entity.ProjetConstruction;

public interface ProjetConstructionService {

    ProjetConstruction creerProjet(ProjetConstruction projetConstruction);
 
    List<ProjetConstruction> listerProjets();

    ProjetConstruction getProjet(Long id);

    ProjetConstruction affecterEmploye(Long projetId, Long employeId);
}
