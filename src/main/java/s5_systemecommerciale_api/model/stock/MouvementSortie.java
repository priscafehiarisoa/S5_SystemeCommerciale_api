package s5_systemecommerciale_api.model.stock;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MouvementSortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long idStock;
    private double quantite_mouvement_sortie;
    private double prixUnitaire;
    @ManyToOne
    private SortieStock sortieStock;

    public MouvementSortie(Long id, Long idStock, double quantite_mouvement_sortie, SortieStock sortieStock,double prixUnitaire) {
        this.id = id;
        this.idStock = idStock;
        this.quantite_mouvement_sortie = quantite_mouvement_sortie;
        this.sortieStock = sortieStock;
        setPrixUnitaire(prixUnitaire);
    }

    public MouvementSortie(Long idStock, double quantite_mouvement_sortie, SortieStock sortieStock,double prixUnitaire) {
        this.idStock = idStock;
        this.quantite_mouvement_sortie = quantite_mouvement_sortie;
        this.sortieStock = sortieStock;
        setPrixUnitaire(prixUnitaire);
    }
}
