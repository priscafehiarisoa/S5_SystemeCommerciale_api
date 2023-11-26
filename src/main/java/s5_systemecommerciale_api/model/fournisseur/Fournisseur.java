package s5_systemecommerciale_api.model.fournisseur;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Entity
@Getter
@Setter
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="fournisseur_seq" )
    @SequenceGenerator(name = "fournisseur_seq" , sequenceName = "fournisseur_seq", allocationSize = 1)
    @Column(name="id_fournisseur")
    Long id;

    String nom_fournisseur;
    String Adresse;
    String telephone;
    String nom_responsable;
    double prix_livraison;
    String email;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fournisseur", cascade = CascadeType.ALL)
//    List<Produit_Fournisseur> produitFournisseurs;

    public Fournisseur(Long id, String nom_fournisseur, String adresse, String telephone, String nom_responsable, double prix_livraison, String email) {
        setId(id);
        setNom_fournisseur(nom_fournisseur);
        setAdresse(adresse);
        setTelephone(telephone);
        setNom_responsable(nom_responsable);
        setPrix_livraison(prix_livraison);
        setEmail(email);
    }

    public Fournisseur(String nom_fournisseur, String adresse, String telephone, String nom_responsable, double prix_livraison,String email) {
        setNom_fournisseur(nom_fournisseur);
        setAdresse(adresse);
        setTelephone(telephone);
        setNom_responsable(nom_responsable);
        setPrix_livraison(prix_livraison);
        setEmail(email);
    }

    public Fournisseur() {
    }

//    public List<Produit_Fournisseur> getProduitFournisseurs() {
//        return produitFournisseurs;
//    }
//
//    public void setProduitFournisseurs(List<Produit_Fournisseur> produitFournisseurs) {
//        this.produitFournisseurs = produitFournisseurs;
//    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + id +
                ", nom_fournisseur='" + nom_fournisseur + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", nom_responsable='" + nom_responsable + '\'' +
                ", prix_livraison=" + prix_livraison +
                '}';
    }

    public void setPrix_livraison(double prix_livraison) {
        this.prix_livraison = prix_livraison;
    }

    public void setPrix_livraison(String prix_livraison) throws Exception {
        if(prix_livraison ==null){
            throw new Exception("valeur ne peux pas etre nulle");
        }
        try {
            double prixdouble=Double.parseDouble((prix_livraison));
            if(prixdouble<0)
            {
                throw new Exception(("valeur ne peux pas etre negative"));
            }
            this.prix_livraison = prixdouble;


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }



    }
}
