package s5_systemecommerciale_api.model.besoins;

import jakarta.persistence.*;
import s5_systemecommerciale_api.model.produit.Produit;

@Entity

public class Besoin_produit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="besoin_produit_seq" )
    @SequenceGenerator(name = "besoin_produit_seq" , sequenceName = "besoin_produit_seq", allocationSize = 1)
    @Column(name="id_besoin_produit")
    Long id;
    double quantite;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_besoin")
    Besoin besoin;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    Produit produit;

    public Besoin_produit() {
    }

    public Besoin_produit(double quantite, Besoin besoin, Produit produit) {
        setQuantite(quantite);
        setBesoin(besoin);
        setProduit(produit);
    }

    public Besoin_produit(Long id, double quantite, Besoin besoin, Produit produit) {
        setId(id);
        setQuantite(quantite);
        setBesoin(besoin);
        setProduit(produit);
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

    @Override
    public String toString() {
        return "Besoin_produit{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", besoin=" + besoin.toString() +
                ", produit=" + produit.toString() +
                '}';
    }
}
