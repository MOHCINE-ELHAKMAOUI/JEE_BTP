package ma.fsts.agep_btp;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.fsts.agep_btp.entity.Devis;
import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.Materiau;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.RoleEmploye;
import ma.fsts.agep_btp.entity.StatutProjet;
import ma.fsts.agep_btp.entity.TypeConstruction;
import ma.fsts.agep_btp.repository.*;

@SpringBootApplication
public class AgepBtpApplication {

    

	public static void main(String[] args) {
		SpringApplication.run(AgepBtpApplication.class, args);
	}

	 @Bean
    CommandLineRunner initData(
            EmployeRepository employeRepository,
            MateriauRepository materiauRepository,
            ProjetConstructionRepository projetConstructionRepository,
            DevisRepository devisRepository,
            PasswordEncoder passwordEncoder
            
    ){
        return args -> {

            // ===== Employe =====
            Employe e1 = new Employe();
            e1.setNom("ingenieur1");
			e1.setDisponible(true);
			e1.setRole(RoleEmploye.INGENIEUR);
            e1.setEmail("em1@btp.com");
            e1.setMotDePasse(passwordEncoder.encode("1234"));
            
			Employe e2 = new Employe();
            e2.setNom("ouvrier1");
			e2.setDisponible(true);
			e2.setRole(RoleEmploye.OUVRIER);
            e2.setEmail("em2@btp.com");
            e2.setMotDePasse(passwordEncoder.encode("1234"));

            employeRepository.save(e1);
            employeRepository.save(e2);


            Materiau m1 = new Materiau();
            m1.setNom("CIMENT");
            m1.setPrixUnitaire(150);
            m1.setUnite("unité");
            
            materiauRepository.save(m1);
            
            
            Materiau m2 = new Materiau();
            m2.setNom("SABLE");
            m2.setPrixUnitaire(10);
            m2.setUnite("kg");
            
            materiauRepository.save(m2);            
            
            Materiau m3 = new Materiau();
            m3.setNom("GRAVIER");
            m3.setPrixUnitaire(8);
            m3.setUnite("kg");

            materiauRepository.save(m3);
            
            Materiau m4 = new Materiau();
            m4.setNom("BRIQUES");
            m4.setPrixUnitaire(3);
            m4.setUnite("unité");

            materiauRepository.save(m4);
            
            Materiau m5 = new Materiau();
            m5.setNom("FER");
            m5.setPrixUnitaire(30);
            m5.setUnite("barre");

            materiauRepository.save(m5);

            ProjetConstruction p1 = new ProjetConstruction();
            p1.setTypeConstruction(TypeConstruction.MAISON);
            p1.setSuperficieConstruite(150);
            // p1.setTerrain(t1);
            p1.setStatut(StatutProjet.EN_COURS);
            p1.setMateriaux(null);

            ProjetConstruction p2 = new ProjetConstruction();
            p2.setTypeConstruction(TypeConstruction.MAISON);
            p2.setSuperficieConstruite(150);
            // p2.setTerrain(t1);
            p2.setStatut(StatutProjet.EN_COURS);
            p2.setMateriaux(null);

            projetConstructionRepository.save(p1);
            projetConstructionRepository.save(p2);

            Devis d1 = new Devis();
            d1.setDateCreation(LocalDate.now());
            d1.setMontantTotal(250000);
            d1.setProjet(p2);
            
            devisRepository.save(d1);

        };
    }

}