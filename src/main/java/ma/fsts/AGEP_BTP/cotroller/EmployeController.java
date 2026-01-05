package ma.fsts.AGEP_BTP.cotroller;

import lombok.RequiredArgsConstructor;
import ma.fsts.AGEP_BTP.entity.Employe;
import ma.fsts.AGEP_BTP.service.EmployeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService employeService;

    @PostMapping
    public Employe ajouterEmploye(@RequestBody Employe employe) {
        return employeService.ajouterEmploye(employe);
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
}
