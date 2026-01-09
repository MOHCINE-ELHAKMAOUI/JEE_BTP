package ma.fsts.agep_btp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.Materiau;
import ma.fsts.agep_btp.entity.ProjetConstruction;
import ma.fsts.agep_btp.entity.RoleEmploye;
import ma.fsts.agep_btp.entity.StatutProjet;
import ma.fsts.agep_btp.entity.Terrain;
import ma.fsts.agep_btp.entity.TypeConstruction;
import ma.fsts.agep_btp.repository.EmployeRepository;
import ma.fsts.agep_btp.repository.MateriauRepository;
import ma.fsts.agep_btp.repository.ProjetConstructionRepository;
import ma.fsts.agep_btp.repository.TerrainRepository;

@SpringBootApplication
public class AgepBtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgepBtpApplication.class, args);
	}

	 @Bean
    CommandLineRunner initData(
            EmployeRepository employeRepository,
            TerrainRepository terrainRepository,
            MateriauRepository materiauRepository,
            ProjetConstructionRepository projetConstructionRepository
            
    ){
        return args -> {

            // ===== Employe =====
            Employe e1 = new Employe();
            e1.setNom("ingenieur1");
			e1.setDisponible(true);
			e1.setRole(RoleEmploye.INGENIEUR);
            
			Employe e2 = new Employe();
            e2.setNom("ouvrier1");
			e2.setDisponible(true);
			e2.setRole(RoleEmploye.OUVRIER);

            employeRepository.save(e1);
            employeRepository.save(e2);

            // ===== Terrain =====
            Terrain t1 = new Terrain();
			t1.setLocalisation("localisation1");
			t1.setSuperficie(100);
            
			Terrain t2 = new Terrain();
			t2.setLocalisation("localisation2");
			t2.setSuperficie(100);
            
            terrainRepository.save(t1);
            terrainRepository.save(t2);

            Materiau m1 = new Materiau();
            m1.setNom("sement");
            m1.setPrixUnitaire(150);
            m1.setUnite("unité");
            
            Materiau m2 = new Materiau();
            m2.setNom("Bricks");
            m2.setPrixUnitaire(10);
            m2.setUnite("unité");

            materiauRepository.save(m1);
            materiauRepository.save(m2);            

            ProjetConstruction p1 = new ProjetConstruction();
            p1.setTypeConstruction(TypeConstruction.MAISON);
            p1.setSuperficieConstruite(150);
            p1.setTerrain(t1);
            p1.setStatut(StatutProjet.EN_COURS);
            p1.setMateriaux(null);

            ProjetConstruction p2 = new ProjetConstruction();
            p2.setTypeConstruction(TypeConstruction.MAISON);
            p2.setSuperficieConstruite(150);
            p2.setTerrain(t1);
            p2.setStatut(StatutProjet.EN_COURS);
            p2.setMateriaux(null);

            projetConstructionRepository.save(p1);
            projetConstructionRepository.save(p2);


        };
    }

}