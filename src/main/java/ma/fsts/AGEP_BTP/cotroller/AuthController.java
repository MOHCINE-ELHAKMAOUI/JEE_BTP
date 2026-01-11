package ma.fsts.agep_btp.cotroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.fsts.agep_btp.dto.AuthResponse;
import ma.fsts.agep_btp.dto.LoginDTO;
import ma.fsts.agep_btp.dto.RegisterDTO;
import ma.fsts.agep_btp.entity.Employe;
import ma.fsts.agep_btp.service.AuthService;
import ma.fsts.agep_btp.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {

        Employe user = authService.login(dto.email(), dto.motDePasse());

        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(
            new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().name(),
                token
            )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto) {

        Employe user = authService.register(dto);

        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(
            new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().name(),
                token
            )
        );
    }
}
