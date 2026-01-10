package ma.fsts.agep_btp.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.ProjetMateriau;
import ma.fsts.agep_btp.repository.DevisRepository;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;
import ma.fsts.agep_btp.repository.ProjetMateriauRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DevisServiceImpl implements DevisService {

    private final DevisRepository devisRepository;
    private final ProjetConstructionRepository projetConstructionRepository;
    private final ProjetMateriauRepository projetMateriauRepository;

    @Override
    public Devis genererDevis(Long projetId) {

        ProjetConstruction projet = projetConstructionRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));

        // calcul du coût total à partir des matériaux
        double total = 0.0;
        if (projet.getMateriaux() != null && !projet.getMateriaux().isEmpty()) {
            total = projet.getMateriaux()
                    .stream()
                    .mapToDouble(ProjetMateriau::getCout)
                    .sum();
        } else {
            // Use coutTotal if materiaux are not loaded or empty
            total = projet.getCoutTotal();
        }

        Devis devis = devisRepository
                .findByProjetId(projetId)
                .orElse(new Devis());

        devis.setProjet(projet);
        devis.setDateCreation(LocalDate.now());
        devis.setMontantTotal(total);

        return devisRepository.save(devis);
    }

    @Override
    public Devis getDevisByProjet(Long projetId) {
        Devis devis = devisRepository.findByProjetId(projetId)
                .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        
        // Ensure the projet with employees and materiaux is loaded
        if (devis.getProjet() != null) {
            ProjetConstruction projet = projetConstructionRepository.findById(projetId)
                    .orElse(null);
            if (projet != null) {
                // Initialize employes list if null
                if (projet.getEmployes() == null) {
                    projet.setEmployes(new ArrayList<>());
                } else {
                    // Trigger lazy loading by accessing the list
                    projet.getEmployes().size();
                }
                // Explicitly load materiaux using repository with materiau entity
                List<ProjetMateriau> materiaux = projetMateriauRepository.findByProjetIdWithMateriau(projetId);
                projet.setMateriaux(materiaux != null ? materiaux : new ArrayList<>());
                // Update devis with the loaded projet
                devis.setProjet(projet);
            }
        }
        
        return devis;
    }

    @Override
    public Devis genererDevis(ProjetConstruction projet) {

        Devis devis = new Devis();
        devis.setProjet(projet);
        devis.setDateCreation(LocalDate.now());
        devis.setMontantTotal(projet.getCoutTotal());

        return devisRepository.save(devis);
    }

    @Override
    public Devis getDevisById(Long id) {
        Devis devis = devisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devis non trouvé avec l'ID: " + id));
        
        // Ensure the projet with employees and materiaux is loaded
        if (devis.getProjet() != null) {
            Long projetId = devis.getProjet().getId();
            ProjetConstruction projet = projetConstructionRepository.findById(projetId)
                    .orElse(null);
            if (projet != null) {
                // Initialize employes list if null
                if (projet.getEmployes() == null) {
                    projet.setEmployes(new ArrayList<>());
                } else {
                    // Trigger lazy loading by accessing the list
                    projet.getEmployes().size();
                }
                // Explicitly load materiaux using repository with materiau entity
                List<ProjetMateriau> materiaux = projetMateriauRepository.findByProjetIdWithMateriau(projetId);
                projet.setMateriaux(materiaux != null ? materiaux : new ArrayList<>());
                // Update devis with the loaded projet
                devis.setProjet(projet);
            }
        }
        
        return devis;
    }

    @Override
    public List<Devis> findAll() {
        return devisRepository.findAll();
    }
}
