package s5_systemecommerciale_api.model.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.Instant;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_stock")
public class VStock {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_produit")
    private Long idProduit;

    @Column(name = "quantite_produit")
    private Double quantiteProduit;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "date_reception")
    private Instant dateReception;

    @Override
    public String toString() {
        return "VStock{" +
                "id=" + id +
                ", idProduit=" + idProduit +
                ", quantiteProduit=" + quantiteProduit +
                ", prixUnitaire=" + prixUnitaire +
                ", dateReception=" + dateReception +
                '}';
    }
}