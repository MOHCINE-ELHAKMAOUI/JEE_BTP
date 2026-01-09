package ma.fsts.AGEP_BTP.service;

import lombok.RequiredArgsConstructor;
import ma.fsts.AGEP_BTP.entity.Employe;
import ma.fsts.AGEP_BTP.entity.ProjetConstruction;
import ma.fsts.AGEP_BTP.repository.EmployeRepository;
import ma.fsts.AGEP_BTP.repository.ProjetConstructionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
