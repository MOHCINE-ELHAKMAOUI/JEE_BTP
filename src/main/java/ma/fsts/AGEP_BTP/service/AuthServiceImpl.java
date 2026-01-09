package ma.fsts.agep_btp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.fsts.agep_btp.entity.Employe;
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
}

