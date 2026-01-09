package ma.fsts.agep_btp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.dto.RegisterDTO;
import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.entity.RoleEmploye;
import ma.fsts.agep_btp.repository.EmployeRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeRepository employeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Employe login(String email, String motDePasse) {

        // 1️⃣ Vérifier existence utilisateur
        Employe employe = employeRepository.findByEmail(email).orElseThrow(() ->
                        new RuntimeException("Email ou mot de passe incorrect")
                );

        // 2️⃣ Vérifier mot de passe
        if (!passwordEncoder.matches(motDePasse, employe.getMotDePasse())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        return employe;
    }

    @Override
    public Employe register(RegisterDTO registerDTO) {
        // Check if email already exists
        if (employeRepository.findByEmail(registerDTO.email()).isPresent()) {
            throw new RuntimeException("Un compte avec cet email existe déjà");
        }

        Employe employe = new Employe();
        employe.setNom(registerDTO.nom());
        employe.setEmail(registerDTO.email());
        employe.setMotDePasse(passwordEncoder.encode(registerDTO.motDePasse()));
        
        // Parse role from string
        try {
            employe.setRole(RoleEmploye.valueOf(registerDTO.role().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rôle invalide. Les rôles valides sont: INGENIEUR, OUVRIER");
        }
        
        employe.setDisponible(true);

        return employeRepository.save(employe);
    }
}

