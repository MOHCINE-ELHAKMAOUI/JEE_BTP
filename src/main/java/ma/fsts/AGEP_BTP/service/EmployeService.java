package ma.fsts.agep_btp.service;

import java.util.List;
import java.util.Optional;

import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.ProjetConstruction;

public interface EmployeService {

    List<Employe> getEmployesDisponibles();

    void affecterEmploye(Long projetId, Long employeId);

    Employe ajouterEmploye(Employe employe);

    List<Employe> findAll();

    Optional<Employe> findById(Long employeId);

    void save(Employe employe);
    
    List<ProjetConstruction> getProjetsByEmploye(Long employeId);

}
