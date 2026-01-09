package ma.fsts.agep_btp.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.repository.EmployeRepository;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;
    private final ProjetConstructionRepository projetRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Employe ajouterEmploye(Employe employe) {
        // Encode password if it's provided and not already encoded
        if (employe.getMotDePasse() != null && !employe.getMotDePasse().startsWith("$2a$")) {
            employe.setMotDePasse(passwordEncoder.encode(employe.getMotDePasse()));
        }
        employe.setDisponible(true);
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> getEmployesDisponibles() {
        return employeRepository.findByDisponibleTrue();
    }

    @Override
    public void affecterEmploye(Long projetId, Long employeId) {

        ProjetConstruction projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));

        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employé introuvable"));

        // Initialize the employes list if it's null
        if (projet.getEmployes() == null) {
            projet.setEmployes(new ArrayList<>());
        }

        // Check if employee is already assigned
        boolean alreadyAssigned = projet.getEmployes().stream()
                .anyMatch(e -> e.getId().equals(employeId));
        if (alreadyAssigned) {
            throw new RuntimeException("Cet employé est déjà affecté à ce projet");
        }

        projet.getEmployes().add(employe);
        employe.setDisponible(false);

        projetRepository.save(projet);
        employeRepository.save(employe);
    }

    @Override
    public List<Employe> findAll() {

        return employeRepository.findAll();
    }

    @Override
    public Optional<Employe> findById(Long employeId) {
        
        return employeRepository.findById(employeId);
    }

    @Override
    public void save(Employe employe) {
        employeRepository.save(employe);
    }

    @Override
    public List<ProjetConstruction> getProjetsByEmploye(Long employeId) {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employé introuvable"));
        
        // Trigger lazy loading of projets
        if (employe.getProjets() != null) {
            employe.getProjets().size();
        }
        
        return employe.getProjets() != null ? employe.getProjets() : new ArrayList<>();
    }
}
