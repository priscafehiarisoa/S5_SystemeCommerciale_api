package s5_systemecommerciale_api.model;

import jakarta.persistence.*;

@Entity
public class Besoin_produit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="besoin_produit_seq" )
    @SequenceGenerator(name = "besoin_produit_seq" , sequenceName = "besoin_produit_seq", allocationSize = 1)
    @Column(name="id_besoin_produit")
    Long id;
    double quantite;

    @ManyToOne
    @JoinColumn(name = "id_besoin")
    Besoin besoin;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    Produit produit;

    public Besoin_produit() {
    }

    public Besoin_produit(double quantite, Besoin besoin, Produit produit) {
        this.quantite = quantite;
        this.besoin = besoin;
        this.produit = produit;
    }

    public Besoin_produit(Long id, double quantite, Besoin besoin, Produit produit) {
        this.id = id;
        this.quantite = quantite;
        this.besoin = besoin;
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Besoin getBesoin() {
        return besoin;
    }

    public void setBesoin(Besoin besoin) {
        this.besoin = besoin;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setQuantite(String quantite) throws Exception {
        if(quantite ==null){
            throw new Exception("valeur ne peux pas etre nulle");
        }
        try {
            double prixdouble=Double.parseDouble((quantite));
            if(prixdouble<0)
            {
                throw new Exception(("valeur ne peux pas etre negative"));
            }
            this.quantite = prixdouble;


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
