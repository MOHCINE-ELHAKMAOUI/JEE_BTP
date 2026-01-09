package ma.fsts.AGEP_BTP.cotroller;

import lombok.RequiredArgsConstructor;
import ma.fsts.AGEP_BTP.entity.Terrain;
import ma.fsts.AGEP_BTP.repository.TerrainRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrains")
@RequiredArgsConstructor
public class TerrainController {

    private final TerrainRepository terrainRepository;

    @PostMapping
    public Terrain create(@RequestBody Terrain terrain) {
        return terrainRepository.save(terrain);
    }

    @GetMapping
    public List<Terrain> getAll() {
        return terrainRepository.findAll();
    }

    @GetMapping("/{id}")
    public Terrain getById(@PathVariable Long id) {
        return terrainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Terrain introuvable"));
    }
}
