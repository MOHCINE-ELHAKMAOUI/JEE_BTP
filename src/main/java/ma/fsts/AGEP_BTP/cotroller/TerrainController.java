// package ma.fsts.agep_btp.cotroller;

// import lombok.RequiredArgsConstructor;
// import ma.fsts.agep_btp.entity.Terrain;
// import ma.fsts.agep_btp.repository.TerrainRepository;

// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/terrains")
// @RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:3000")
// public class TerrainController {

//     private final TerrainRepository terrainRepository;

//     @PostMapping
//     public Terrain create(@RequestBody Terrain terrain) {
//         return terrainRepository.save(terrain);
//     }

//     @GetMapping
//     public List<Terrain> getAll() {
//         return terrainRepository.findAll();
//     }

//     @GetMapping("/{id}")
//     public Terrain getById(@PathVariable Long id) {
//         return terrainRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Terrain introuvable"));
//     }
// }
