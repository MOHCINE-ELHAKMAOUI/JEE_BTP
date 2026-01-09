package ma.fsts.agep_btp.service;

import java.util.List;

import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.TypeConstruction;

public interface ProjetConstructionService {

    ProjetConstruction creerProjet(Long terrainId,
                                   TypeConstruction typeConstruction,
                                   double superficie);

    List<ProjetConstruction> listerProjets();

    ProjetConstruction getProjet(Long id);
}
