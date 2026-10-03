package ma.fsts.agep_btp.service;

import ma.fsts.agep_btp.dto.RegisterDTO;
import ma.fsts.agep_btp.entity.Employe;

public interface AuthService {

    Employe login(String email, String motDePasse);
    
    Employe register(RegisterDTO registerDTO);

}

