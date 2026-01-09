package ma.fsts.agep_btp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjetMateriau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProjetConstruction projet;

    @ManyToOne
    private Materiau materiau;

    private double quantite;

    private double cout;
}

