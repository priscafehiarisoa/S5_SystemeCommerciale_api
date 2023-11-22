package s5_systemecommerciale_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import s5_systemecommerciale_api.repository.BonDeCommandeArticleRepository;
import s5_systemecommerciale_api.repository.BonDeCommandeRepository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class BonsDeCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bons_de_commande", nullable = false)
    private Long id_bonsDeCommande;
    private double totalHorsTaxes;
    private double  totalTTC;
    @ManyToOne
    @JoinColumn(name = "id_fourisseur")
    private Fournisseur fournisseur;
    @Transient
    private List<BonsDeCommandeArticle> listeArticles;
    @ManyToOne
    @JoinColumn(name = "id_besoin")
    private Besoin besoin;



    public BonsDeCommande(Long id_bonsDeCommande, double totalHorsTaxes, double totalTTC, Fournisseur fournisseur,Besoin besoin) {
        setId_bonsDeCommande(id_bonsDeCommande);
        setFournisseur(fournisseur);
        setTotalHorsTaxes(totalHorsTaxes);
        setTotalTTC(totalTTC);
        setBesoin(besoin);
    }
    public BonsDeCommande(List<Article> liste){
        if(!liste.isEmpty()){
            setFournisseur(liste.get(0).getFournisseur());
            setBesoin(liste.get(0).getBesoin());
        }
        double HT=0;
        double TTC=0;
        for (int i = 0; i < liste.size(); i++) {
            HT+=liste.get(i).getPrixHorsTaxe();
            TTC+=liste.get(i).getPrixTTC();
        }
        setTotalTTC(TTC);
        setTotalHorsTaxes(HT);
//        setListeArticles(BonsDeCommandeArticle.getBCFromListArticle(liste,this));
    }

    public BonsDeCommande(double totalHorsTaxes, double totalTTC, Fournisseur fournisseur,Besoin besoin) {
        setTotalHorsTaxes(totalHorsTaxes);
        setTotalTTC(totalTTC);
        setFournisseur(fournisseur);
        setBesoin(besoin);
    }

    public BonsDeCommande() {

    }

    @Transactional
    public void saveBonsDeCommande(BonDeCommandeRepository bonDeCommandeRepository, BonDeCommandeArticleRepository bonDeCommandeArticleRepository){
        bonDeCommandeRepository.save(this);
        BonsDeCommande savedBonsDeCommande = bonDeCommandeRepository.save(this);
        // Update the ID in the original BonsDeCommande instance
        this.setId_bonsDeCommande(savedBonsDeCommande.getId_bonsDeCommande());
        for (int i = 0; i < this.getListeArticles().size(); i++) {
            getListeArticles().get(i).setIdBonDeCommande(this.getId_bonsDeCommande());
            bonDeCommandeArticleRepository.save(this.getListeArticles().get(i));
        }

    }
    @Transactional
    public List<BonsDeCommande> getAllBDC_from_list (List<List<Article>> articles){
        List<BonsDeCommande> bonsDeCommandes=new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            BonsDeCommande bdc= new BonsDeCommande(articles.get(i));
            BonsDeCommande bdc2=new BonsDeCommande(bdc.getId_bonsDeCommande(),bdc.getTotalHorsTaxes(),bdc.getTotalTTC(),bdc.getFournisseur(),bdc.getBesoin());
            bdc.setListeArticles(BonsDeCommandeArticle.getBCFromListArticle(articles.get(i),bdc2));
            bonsDeCommandes.add(bdc);
        }
        return bonsDeCommandes;
    }

}

