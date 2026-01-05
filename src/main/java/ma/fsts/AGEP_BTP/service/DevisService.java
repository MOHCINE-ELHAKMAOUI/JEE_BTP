package ma.fsts.AGEP_BTP.service;

import ma.fsts.AGEP_BTP.entity.Devis;
import ma.fsts.AGEP_BTP.entity.ProjetConstruction;

public interface DevisService {

    Devis genererDevis(ProjetConstruction projet);
}
