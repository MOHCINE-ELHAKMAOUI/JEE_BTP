package ma.fsts.agep_btp.cotroller;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;
import ma.fsts.agep_btp.service.DevisService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/devis")
@RequiredArgsConstructor
public class DevisController {

    private final DevisService devisService;
    private final ProjetConstructionRepository projetRepository;

    @GetMapping
    public List<Devis> getAllDevis() {
        return devisService.findAll();
    }

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
