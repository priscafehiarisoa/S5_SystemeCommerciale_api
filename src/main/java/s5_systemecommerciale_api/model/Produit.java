package s5_systemecommerciale_api.model;

import jakarta.persistence.*;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="produit_seq" )
    @SequenceGenerator(name = "produit_seq" , sequenceName = "produit_seq", allocationSize = 1)
    @Column(name="id_produit")
    Long id;
    String nomProduit;
    int etat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Produit(String nomProduit, int etat) {
        this.nomProduit = nomProduit;
        this.etat = etat;
    }

    public Produit(Long id, String nomProduit, int etat) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.etat = etat;
    }

    public Produit() {
    }
}