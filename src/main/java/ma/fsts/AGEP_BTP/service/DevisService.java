package ma.fsts.agep_btp.service;

import java.util.List;

import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.ProjetConstruction;

public interface DevisService {

    Devis genererDevis(ProjetConstruction projet);
    Devis genererDevis(Long projetId);
    Devis getDevisByProjet(Long projetId);
    List<Devis> findAll();
}
