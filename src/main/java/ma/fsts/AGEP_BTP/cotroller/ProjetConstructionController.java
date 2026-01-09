package ma.fsts.agep_btp.cotroller;
import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.ProjetConstruction;
// import ma.fsts.agep_btp.entity.TypeConstruction;
import ma.fsts.agep_btp.service.ProjetConstructionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProjetConstructionController {

    private final ProjetConstructionService projetService;

    @PostMapping
    public ProjetConstruction creerProjet(@RequestBody ProjetConstruction projet) {
        return projetService.creerProjet(projet);
    }

    // @PostMapping
    // public ProjetConstruction creerProjet(
    //         // @RequestParam Long terrainId,
    //         @RequestParam TypeConstruction typeConstruction,
    //         @RequestParam double superficie
    // ) {
    //     return projetService.creerProjet(typeConstruction, superficie);
    //     // return projetService.creerProjet(terrainId, typeConstruction, superficie);
    // }

    @PostMapping("/{projetId}/employes/{employeId}")
    public ProjetConstruction affecterEmploye(
            @PathVariable Long projetId,
            @PathVariable Long employeId) {
        return projetService.affecterEmploye(projetId, employeId);
    }

    @GetMapping
    public List<ProjetConstruction> getAll() {
        return projetService.listerProjets();
    }

    @GetMapping("/{id}")
    public ProjetConstruction getById(@PathVariable Long id) {
        return projetService.getProjet(id);
    }
}
