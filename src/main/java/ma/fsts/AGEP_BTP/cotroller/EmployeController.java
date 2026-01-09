package ma.fsts.agep_btp.cotroller;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.service.EmployeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService employeService;

    @PostMapping
    public Employe ajouterEmploye(@RequestBody Employe employe) {
        return employeService.ajouterEmploye(employe);
    }

     @GetMapping
    public List<Employe> getAll() {
        return employeService.findAll();
    }

    @GetMapping("/disponibles")
    public List<Employe> getEmployesDisponibles() {
        return employeService.getEmployesDisponibles();
    }

    @PostMapping("/affecter")
    public void affecterEmploye(
            @RequestParam Long projetId,
            @RequestParam Long employeId
    ) {
        employeService.affecterEmploye(projetId, employeId);
    }

    @GetMapping("/{employeId}/projets")
    public List<ProjetConstruction> getProjetsByEmploye(
            @PathVariable Long employeId
    ) {
        return employeService.getProjetsByEmploye(employeId);
    }
}
