package s5_systemecommerciale_api.model.bonDeCommande;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import s5_systemecommerciale_api.model.produit.Article;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class BonsDeCommandeArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_BC_Article", nullable = false)
    private Long id;

    private String designation;
    private double quantite;
    private double prixHorsTaxe;
    private double prixTTC;
    private double prixUnitaire;
    private Long idBonDeCommande;

    public BonsDeCommandeArticle() {

    }

    @Override
    public String toString() {
        return "BonsDeCommandeArticle{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", quantite=" + quantite +
                ", prixHorsTaxe=" + prixHorsTaxe +
                ", prixTTC=" + prixTTC +
                ", prixUnitaire=" + prixUnitaire +
                ", bonsDeCommande=" + idBonDeCommande +
                '}';
    }

    public BonsDeCommandeArticle(BonsDeCommande bonsDeCommande, Article article) {
        setIdBonDeCommande(bonsDeCommande.getId_bonsDeCommande());
        setDesignation(article.getDesignation());
        setQuantite(article.getQuantite());
        setPrixHorsTaxe(article.getPrixHorsTaxe());
        setPrixTTC(article.getPrixTTC());
        setPrixUnitaire(article.getPrixUnitaire());
    }

    public BonsDeCommandeArticle(Long id, String designation, double quantite, double prixHorsTaxe, double prixTTC, double prixUnitaire, BonsDeCommande bonsDeCommande) {
        this.id = id;
        this.designation = designation;
        this.quantite = quantite;
        this.prixHorsTaxe = prixHorsTaxe;
        this.prixTTC = prixTTC;
        this.prixUnitaire = prixUnitaire;
        this.idBonDeCommande = bonsDeCommande.getId_bonsDeCommande();
    }

    public BonsDeCommandeArticle(String designation, double quantite, double prixHorsTaxe, double prixTTC, double prixUnitaire, BonsDeCommande bonsDeCommande) {
        this.designation = designation;
        this.quantite = quantite;
        this.prixHorsTaxe = prixHorsTaxe;
        this.prixTTC = prixTTC;
        this.prixUnitaire = prixUnitaire;
        this.idBonDeCommande = bonsDeCommande.getId_bonsDeCommande();
    }

    public static List<BonsDeCommandeArticle> getBCFromListArticle(List<Article> articles, BonsDeCommande bonsDeCommande){
        List<BonsDeCommandeArticle> bonsDeCommandeArticles=new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            bonsDeCommandeArticles.add(new BonsDeCommandeArticle(bonsDeCommande,articles.get(i)));
        }
        return bonsDeCommandeArticles;
    }
}
