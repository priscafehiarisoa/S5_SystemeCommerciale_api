package s5_systemecommerciale_api.model.BonsDeReception;

import jakarta.persistence.*;
import lombok.Data;
import s5_systemecommerciale_api.model.produit.Produit;

@Entity
@Data
public class DetailsBonsReception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idBonReception")
    private BonsDeReception bonsDeReception;
    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;
    private double quantiteProduit;


}
