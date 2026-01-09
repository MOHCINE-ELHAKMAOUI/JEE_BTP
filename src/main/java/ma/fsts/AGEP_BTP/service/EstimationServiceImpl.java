package ma.fsts.agep_btp.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Materiau;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.ProjetMateriau;
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

    private static final Map<String, Double> COEFFICIENTS = Map.of(
            "CIMENT", 8.0,
            "SABLE", 0.05,
            "GRAVIER", 0.07,
            "BRIQUES", 50.0,
            "FER", 10.0
    );

    @Override
    public void calculerMateriauxEtCout(ProjetConstruction projet) {

        double superficie = projet.getSuperficieConstruite();
        double coutTotal = 0;

        List<ProjetMateriau> projetMateriaux = new ArrayList<>();

        for (Map.Entry<String, Double> entry : COEFFICIENTS.entrySet()) {

            Materiau materiau = materiauRepository.findByNom(entry.getKey())
                    .orElseThrow(() ->
                        new RuntimeException("Matériau introuvable : " + entry.getKey())
                    );

            double quantite = superficie * entry.getValue();
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
