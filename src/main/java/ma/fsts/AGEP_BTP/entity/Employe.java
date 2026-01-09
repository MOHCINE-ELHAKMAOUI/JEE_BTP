package ma.fsts.agep_btp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private RoleEmploye role;

    private boolean disponible;

    @ManyToMany(mappedBy = "employes")
    private List<ProjetConstruction> projets;
}

