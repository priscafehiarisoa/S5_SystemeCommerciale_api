package s5_systemecommerciale_api.model.stock;

import jakarta.persistence.*;
import lombok.Data;
import s5_systemecommerciale_api.model.produit.Produit;
import s5_systemecommerciale_api.repository.VStockRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SortieStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Produit produit;
    private double quantite_sortie;
    private Timestamp dateSortie;

    public SortieStock(Long id, Produit produit, double quantite_sortie, Timestamp dateSortie) {
        this.id = id;
        this.produit = produit;
        this.quantite_sortie = quantite_sortie;
        this.dateSortie = dateSortie;
    }

    public SortieStock(Produit produit, double quantite_sortie, Timestamp dateSortie) {
        this.produit = produit;
        this.quantite_sortie = quantite_sortie;
        this.dateSortie = dateSortie;
    }

    public SortieStock() {
    }

    public List<MouvementSortie> decomposition (VStockRepository vStockRepository){
        String order = this.getProduit().getTypeSortie()==0?"ASC":"DESC";
        double totakeOut=this.getQuantite_sortie();
        List<VStock> restesStock= vStockRepository.getResteStockProduitOrdered(this.getProduit().getId(),order);
        List<MouvementSortie> mouvementSorties= new ArrayList<>();
        for (int i = 0; totakeOut!=0 && i< restesStock.size(); i++) {
            double take=0;
            double prix= restesStock.get(i).getPrixUnitaire();
            if(restesStock.get(i).getQuantiteProduit()<totakeOut){
                take=restesStock.get(i).getQuantiteProduit();
                totakeOut-=take;
            }
            else if(restesStock.get(i).getQuantiteProduit()>totakeOut){
                take=totakeOut;
                totakeOut=0;
            }
            mouvementSorties.add(new MouvementSortie(restesStock.get(i).getId(),take,this,prix));
        }
        return mouvementSorties;
    }


}
