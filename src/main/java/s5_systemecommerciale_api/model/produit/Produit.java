package s5_systemecommerciale_api.model.produit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="produit_seq" )
    @SequenceGenerator(name = "produit_seq" , sequenceName = "produit_seq", allocationSize = 1)
    @Column(name="id_produit")
    Long id;
    String nomProduit;
    int etat;
    @ManyToOne
    UniteMesure unite;

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
        setId(id);
        setNomProduit(nomProduit);
        setEtat(etat);
    }
    public Produit(String nomProduit){
        setNomProduit(nomProduit);
    }

    public Produit() {
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nomProduit='" + nomProduit + '\'' +
                ", etat=" + etat +
                ", unite=" + unite +
                '}';
    }
}
