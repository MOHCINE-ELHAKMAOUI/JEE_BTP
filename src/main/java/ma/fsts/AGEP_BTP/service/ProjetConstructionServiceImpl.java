package ma.fsts.agep_btp.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.ProjetConstruction;
// import ma.fsts.agep_btp.entity.Terrain;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;
// import ma.fsts.agep_btp.repository.TerrainRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetConstructionServiceImpl implements ProjetConstructionService {

    private final ProjetConstructionRepository projetRepository;
    private final EstimationService estimationService;
    private final EmployeService employeService;

    @Override
    public ProjetConstruction creerProjet(ProjetConstruction projet){

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

    @Override
    public ProjetConstruction affecterEmploye(Long projetId, Long employeId) {
        ProjetConstruction projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));

        Employe employe = employeService.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));

        if (!employe.isDisponible()) {
            throw new RuntimeException("Employé indisponible");
        }

        projet.getEmployes().add(employe);
        employe.setDisponible(false);

        employeService.save(employe);
        return projetRepository.save(projet);
    }
}
