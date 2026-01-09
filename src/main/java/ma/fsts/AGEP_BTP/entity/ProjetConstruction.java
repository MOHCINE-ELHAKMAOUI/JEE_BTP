package ma.fsts.AGEP_BTP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjetConstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeConstruction typeConstruction;

    private double superficieConstruite;

    private double coutTotal;

    @Enumerated(EnumType.STRING)
    private StatutProjet statut;

    @ManyToOne
    private Terrain terrain;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<ProjetMateriau> materiaux;

    @ManyToMany
    @JoinTable(
        name = "projet_employe",
        joinColumns = @JoinColumn(name = "projet_id"),
        inverseJoinColumns = @JoinColumn(name = "employe_id")
    )
    private List<Employe> employes;
}

