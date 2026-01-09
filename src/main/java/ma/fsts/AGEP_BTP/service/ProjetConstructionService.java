package ma.fsts.AGEP_BTP.service;

import java.util.List;

import ma.fsts.AGEP_BTP.entity.ProjetConstruction;
import ma.fsts.AGEP_BTP.entity.TypeConstruction;

public interface ProjetConstructionService {

    ProjetConstruction creerProjet(Long terrainId,
                                   TypeConstruction typeConstruction,
                                   double superficie);

    List<ProjetConstruction> listerProjets();

    ProjetConstruction getProjet(Long id);
}
