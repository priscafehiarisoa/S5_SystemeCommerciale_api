package s5_systemecommerciale_api;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "reste")
public class Reste {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_produit")
    private Long idProduit;

    @Column(name = "quantite_produit")
    private Double quantiteProduit;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

}