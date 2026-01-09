package ma.fsts.agep_btp.service;

import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.ProjetConstruction;

public interface DevisService {

    Devis genererDevis(ProjetConstruction projet);
}
