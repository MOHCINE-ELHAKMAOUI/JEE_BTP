package ma.fsts.agep_btp.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.repository.DevisRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DevisServiceImpl implements DevisService {

    private final DevisRepository devisRepository;

    @Override
    public Devis genererDevis(ProjetConstruction projet) {

        Devis devis = new Devis();
        devis.setProjet(projet);
        devis.setDateCreation(LocalDate.now());
        devis.setMontantTotal(projet.getCoutTotal());

        return devisRepository.save(devis);
    }
}
