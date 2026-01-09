package ma.fsts.agep_btp.cotroller;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;
import ma.fsts.agep_btp.service.DevisService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devis")
@RequiredArgsConstructor
public class DevisController {

    private final DevisService devisService;
    private final ProjetConstructionRepository projetRepository;

    @PostMapping("/generer/{projetId}")
    public Devis genererDevis(@PathVariable Long projetId) {

        ProjetConstruction projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));

        return devisService.genererDevis(projet);
    }

    @GetMapping("/projet/{projetId}")
    public Devis getDevisByProjet(@PathVariable Long projetId) {

        ProjetConstruction projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));

        return devisService.genererDevis(projet);
    }
}
