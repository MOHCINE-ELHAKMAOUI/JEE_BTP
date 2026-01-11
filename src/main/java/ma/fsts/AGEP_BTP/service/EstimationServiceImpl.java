package ma.fsts.agep_btp.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Materiau;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.ProjetMateriau;
import ma.fsts.agep_btp.entity.TypeConstruction;
import ma.fsts.agep_btp.repository.MateriauRepository;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;
import ma.fsts.agep_btp.repository.ProjetMateriauRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EstimationServiceImpl implements EstimationService {

    private final MateriauRepository materiauRepository;
    private final ProjetMateriauRepository projetMateriauRepository;
    private final ProjetConstructionRepository projetRepository;

    private static final Map<String, Double> COEFFICIENTS_BASE = Map.of(
            "CIMENT", 8.0,
            "SABLE", 0.05,
            "GRAVIER", 0.07,
            "BRIQUES", 50.0,
            "FER", 10.0
    );

    // Multipliers for each construction type
    private static final Map<TypeConstruction, Double> TYPE_MULTIPLIERS = Map.of(
            TypeConstruction.MAISON, 1.0,
            TypeConstruction.VILLA, 1.3,
            TypeConstruction.RDC, 0.9,
            TypeConstruction.R_PLUS_1, 1.1,
            TypeConstruction.R_PLUS_2, 1.2,
            TypeConstruction.IMMEUBLE, 1.5
    );

    @Override
    public void calculerMateriauxEtCout(ProjetConstruction projet) {

        double superficie = projet.getSuperficieConstruite();
        double coutTotal = 0;

        // Get multiplier for construction type
        TypeConstruction typeConstruction = projet.getTypeConstruction();
        Double typeMultiplier = TYPE_MULTIPLIERS.getOrDefault(typeConstruction, 1.0);

        List<ProjetMateriau> projetMateriaux = new ArrayList<>();

        for (Map.Entry<String, Double> entry : COEFFICIENTS_BASE.entrySet()) {

            Materiau materiau = materiauRepository.findByNom(entry.getKey())
                    .orElseThrow(() ->
                        new RuntimeException("Matériau introuvable : " + entry.getKey())
                    );

            // Apply construction type multiplier to the coefficient
            double coefficientAjuste = entry.getValue() * typeMultiplier;
            double quantite = superficie * coefficientAjuste;
            double cout = quantite * materiau.getPrixUnitaire();

            ProjetMateriau pm = new ProjetMateriau();
            pm.setProjet(projet);
            pm.setMateriau(materiau);
            pm.setQuantite(quantite);
            pm.setCout(cout);

            projetMateriaux.add(pm);
            coutTotal += cout;
        }

        projet.setMateriaux(projetMateriaux);
        projet.setCoutTotal(coutTotal);

        projetRepository.save(projet);
        projetMateriauRepository.saveAll(projetMateriaux);
    }
}
