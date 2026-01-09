package ma.fsts.AGEP_BTP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ma.fsts.AGEP_BTP.entity.Employe;
import ma.fsts.AGEP_BTP.entity.RoleEmploye;
import ma.fsts.AGEP_BTP.entity.Terrain;
import ma.fsts.AGEP_BTP.repository.EmployeRepository;
import ma.fsts.AGEP_BTP.repository.TerrainRepository;

@SpringBootApplication
public class AgepBtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgepBtpApplication.class, args);
	}

	 @Bean
    CommandLineRunner initData(
            EmployeRepository employeRepository,
            TerrainRepository terrainRepository
            
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
            
        };
    }

}
