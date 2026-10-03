package ma.fsts.agep_btp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String email;

    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private RoleEmploye role;

    private boolean disponible;

    @ManyToMany(mappedBy = "employes")
    @JsonIgnore
    private List<ProjetConstruction> projets;
}

