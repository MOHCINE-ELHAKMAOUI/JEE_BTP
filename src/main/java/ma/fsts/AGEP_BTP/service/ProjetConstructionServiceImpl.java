package ma.fsts.agep_btp.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.StatutProjet;
import ma.fsts.agep_btp.entity.Terrain;
import ma.fsts.agep_btp.entity.TypeConstruction;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;
import ma.fsts.agep_btp.repository.TerrainRepository;

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
