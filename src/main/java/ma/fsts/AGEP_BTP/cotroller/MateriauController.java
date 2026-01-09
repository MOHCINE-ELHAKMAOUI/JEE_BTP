package ma.fsts.AGEP_BTP.cotroller;

import lombok.RequiredArgsConstructor;
import ma.fsts.AGEP_BTP.entity.Materiau;
import ma.fsts.AGEP_BTP.repository.MateriauRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiaux")
@RequiredArgsConstructor
public class MateriauController {

    private final MateriauRepository materiauRepository;

    @PostMapping
    public Materiau create(@RequestBody Materiau materiau) {
        return materiauRepository.save(materiau);
    }

    @GetMapping
    public List<Materiau> getAll() {
        return materiauRepository.findAll();
    }

    @GetMapping("/{id}")
    public Materiau getById(@PathVariable Long id) {
        return materiauRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matériau introuvable"));
    }
}
