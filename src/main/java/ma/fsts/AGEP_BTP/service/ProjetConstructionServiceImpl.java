package ma.fsts.AGEP_BTP.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.AGEP_BTP.entity.ProjetConstruction;
import ma.fsts.AGEP_BTP.entity.StatutProjet;
import ma.fsts.AGEP_BTP.entity.Terrain;
import ma.fsts.AGEP_BTP.entity.TypeConstruction;
import ma.fsts.AGEP_BTP.repository.ProjetConstructionRepository;
import ma.fsts.AGEP_BTP.repository.TerrainRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetConstructionServiceImpl implements ProjetConstructionService {

    private final ProjetConstructionRepository projetRepository;
    private final TerrainRepository terrainRepository;
    private final EstimationService estimationService;

    @Override
    public ProjetConstruction creerProjet(Long terrainId,
                                          TypeConstruction typeConstruction,
                                          double superficie) {

        Terrain terrain = terrainRepository.findById(terrainId)
                .orElseThrow(() -> new RuntimeException("Terrain introuvable"));

        ProjetConstruction projet = new ProjetConstruction();
        projet.setTerrain(terrain);
        projet.setTypeConstruction(typeConstruction);
        projet.setSuperficieConstruite(superficie);
        projet.setStatut(StatutProjet.ESTIMATION);

        projetRepository.save(projet);

        estimationService.calculerMateriauxEtCout(projet);

        return projet;
    }

    @Override
    public List<ProjetConstruction> listerProjets() {
        return projetRepository.findAll();
    }

    @Override
    public ProjetConstruction getProjet(Long id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));
    }
}
