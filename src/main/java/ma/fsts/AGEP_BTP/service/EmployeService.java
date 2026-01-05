package ma.fsts.AGEP_BTP.service;

import java.util.List;

import ma.fsts.AGEP_BTP.entity.Employe;

public interface EmployeService {

    List<Employe> getEmployesDisponibles();

    void affecterEmploye(Long projetId, Long employeId);

    Employe ajouterEmploye(Employe employe);

}
