package ma.fsts.agep_btp.dto;

public record RegisterDTO(
    String nom,
    String email,
    String motDePasse,
    String role
) {}
