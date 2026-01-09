package ma.fsts.agep_btp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @OneToOne
    @JsonIgnore
    private Devis devis;

    // @ManyToOne
    // private Terrain terrain;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjetMateriau> materiaux;

    @ManyToMany
    @JoinTable(
        name = "projet_employe",
        joinColumns = @JoinColumn(name = "projet_id"),
        inverseJoinColumns = @JoinColumn(name = "employe_id")
    )
    @JsonIgnoreProperties({"projets", "motDePasse"}) // Prevent circular reference and hide sensitive data
    private List<Employe> employes;
}

