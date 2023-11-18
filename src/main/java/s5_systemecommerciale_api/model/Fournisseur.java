package s5_systemecommerciale_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
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

    public Fournisseur(Long id, String nom_fournisseur, String adresse, String telephone, String nom_responsable, double prix_livraison) {
        this.id = id;
        this.nom_fournisseur = nom_fournisseur;
        Adresse = adresse;
        this.telephone = telephone;
        this.nom_responsable = nom_responsable;
        this.prix_livraison = prix_livraison;
    }

    public Fournisseur(String nom_fournisseur, String adresse, String telephone, String nom_responsable, double prix_livraison) {
        this.nom_fournisseur = nom_fournisseur;
        Adresse = adresse;
        this.telephone = telephone;
        this.nom_responsable = nom_responsable;
        this.prix_livraison = prix_livraison;
    }

    public Fournisseur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_fournisseur() {
        return nom_fournisseur;
    }

    public void setNom_fournisseur(String nom_fournisseur) {
        this.nom_fournisseur = nom_fournisseur;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNom_responsable() {
        return nom_responsable;
    }

    public void setNom_responsable(String nom_responsable) {
        this.nom_responsable = nom_responsable;
    }

    public double getPrix_livraison() {
        return prix_livraison;
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
