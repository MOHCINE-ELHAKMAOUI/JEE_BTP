package ma.fsts.agep_btp.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.repository.EmployeRepository;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;
    private final ProjetConstructionRepository projetRepository;

    @Override
    public Employe ajouterEmploye(Employe employe) {
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
}
