package ma.fsts.agep_btp.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.ProjetMateriau;
import ma.fsts.agep_btp.repository.DevisRepository;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DevisServiceImpl implements DevisService {

    private final DevisRepository devisRepository;
    private final ProjetConstructionRepository projetConstructionRepository;

    @Override
    public Devis genererDevis(Long projetId) {

        ProjetConstruction projet = projetConstructionRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));

        // calcul du coût total à partir des matériaux
        double total = projet.getMateriaux()
                .stream()
                .mapToDouble(ProjetMateriau::getCout)
                .sum();

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
        
        // Ensure the projet with employees is loaded
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
        
        // Ensure the projet with employees is loaded
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
