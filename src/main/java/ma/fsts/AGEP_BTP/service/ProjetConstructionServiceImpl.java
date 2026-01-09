package ma.fsts.agep_btp.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetConstructionServiceImpl implements ProjetConstructionService {

    private final ProjetConstructionRepository projetRepository;
    private final EstimationService estimationService;
    private final EmployeService employeService;
    private final DevisService devisService;

    @Override
    public ProjetConstruction creerProjet(ProjetConstruction projet){

        projetRepository.save(projet);

        estimationService.calculerMateriauxEtCout(projet);

        // Automatically create devis after calculating materials and costs
        Devis devis = devisService.genererDevis(projet);
        projet.setDevis(devis);
        ProjetConstruction savedProjet = projetRepository.save(projet);
        
        // Refresh to ensure all relationships are loaded
        projetRepository.flush();
        
        return savedProjet;
    }

    @Override
    public List<ProjetConstruction> listerProjets() {
        return projetRepository.findAll();
    }

    @Override
    public ProjetConstruction getProjet(Long id) {
        ProjetConstruction projet = projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));
        
        // Trigger lazy loading of employees
        if (projet.getEmployes() != null) {
            projet.getEmployes().size();
        }
        
        return projet;
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

        // Initialize the employes list if it's null
        if (projet.getEmployes() == null) {
            projet.setEmployes(new ArrayList<>());
        }

        // Check if employee is already assigned to this project
        boolean alreadyAssigned = projet.getEmployes().stream()
                .anyMatch(e -> e.getId().equals(employeId));
        if (alreadyAssigned) {
            throw new RuntimeException("Cet employé est déjà affecté à ce projet");
        }

        projet.getEmployes().add(employe);
        employe.setDisponible(false);

        employeService.save(employe);
        return projetRepository.save(projet);
    }
}
