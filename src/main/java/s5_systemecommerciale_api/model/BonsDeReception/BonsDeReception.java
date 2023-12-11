package s5_systemecommerciale_api.model.BonsDeReception;

import jakarta.persistence.*;
import lombok.Data;
import s5_systemecommerciale_api.model.Factures.FactureFournisseur;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommande;
import s5_systemecommerciale_api.model.user.Users;

import java.time.LocalDateTime;

@Data
@Entity
public class BonsDeReception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idFactureFournisseur")
    private FactureFournisseur factureFournisseur;
    @ManyToOne
    @JoinColumn(name = "idBonsDeCommande")
    private BonsDeCommande bonsDeCommande;
    @ManyToOne
    @JoinColumn(name = "idMagasinier")
    private Users magasinier;
    private LocalDateTime dateReception;
    private String nomLivreur;

    public BonsDeReception(Long id, FactureFournisseur factureFournisseur, BonsDeCommande bonsDeCommande, Users magasinier, LocalDateTime dateReception, String nomLivreur) {
        this.id = id;
        this.factureFournisseur = factureFournisseur;
        this.bonsDeCommande = bonsDeCommande;
        this.magasinier = magasinier;
        this.dateReception = dateReception;
        this.nomLivreur = nomLivreur;
    }

    public BonsDeReception(FactureFournisseur factureFournisseur, BonsDeCommande bonsDeCommande, Users magasinier, LocalDateTime dateReception, String nomLivreur) {
        this.factureFournisseur = factureFournisseur;
        this.bonsDeCommande = bonsDeCommande;
        this.magasinier = magasinier;
        this.dateReception = dateReception;
        this.nomLivreur = nomLivreur;
    }

    public BonsDeReception() {
    }
}
