package ma.fsts.agep_btp.service;

import java.util.List;

import ma.fsts.agep_btp.entity.Employe;

public interface EmployeService {

    List<Employe> getEmployesDisponibles();

    void affecterEmploye(Long projetId, Long employeId);

    Employe ajouterEmploye(Employe employe);

}
