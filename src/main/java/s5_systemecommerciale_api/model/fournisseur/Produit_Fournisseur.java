package s5_systemecommerciale_api.model.fournisseur;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import s5_systemecommerciale_api.model.produit.Produit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Produit_Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="produit_fournisseur_seq" )
    @SequenceGenerator(name = "produit_fournisseur_seq" , sequenceName = "produit_fournisseur_seq", allocationSize = 1)
    @Column(name="id_produit_fournisseur")
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    Fournisseur fournisseur;

    @ManyToOne
//            (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produit")
    Produit produit;

    double taxe;
    double delaiDeLivraison;
    int etat;
    Timestamp Date;
    double prixHorsTaxe;
    double quantiteStock;

    public Produit_Fournisseur(Long id, Fournisseur fournisseur, Produit produit, double taxe, double delaiDeLivraison, int etat, Timestamp date,double quantiteStock) {
        setId(id);
        setFournisseur(fournisseur);
        setProduit(produit);
        setTaxe(taxe);
        setDelaiDeLivraison(delaiDeLivraison);
        setEtat(etat);
        setDate(date);
        setQuantiteStock(quantiteStock);
    }

    public Produit_Fournisseur(Fournisseur fournisseur, Produit produit, double taxe, double delaiDeLivraison, int etat, Timestamp date,double prixHorsTaxe,double quantiteStock) {
        setFournisseur(fournisseur);
        setProduit(produit);
        setTaxe(taxe);
        setDelaiDeLivraison(delaiDeLivraison);
        setEtat(etat);
        setDate(date);
        setPrixHorsTaxe(prixHorsTaxe);
        setQuantiteStock(quantiteStock);
    }


    public Produit_Fournisseur() {
    }

    public double getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(double quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public double getPrixHorsTaxe() {
        return prixHorsTaxe;
    }

    public void setPrixHorsTaxe(double prixHorsTaxe) {
        this.prixHorsTaxe = prixHorsTaxe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getTaxe() {
        return taxe;
    }
    public void setTaxe(String taxe) throws Exception {
        if(taxe ==null){
            throw new Exception("taxe ne peux pas etre nulle");
        }
        try {
            double prixdouble=Double.parseDouble((taxe));
            if(prixdouble<0)
            {
                throw new Exception(("valeur ne peux pas etre negative"));
            }
            this.taxe = prixdouble;


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void setTaxe(double taxe) {
        this.taxe = taxe;
    }

    public double getDelaiDeLivraison() {
        return delaiDeLivraison;
    }

    public void setDelaiDeLivraison(String delaiDeLivraison) throws Exception {
        if(delaiDeLivraison ==null){
            throw new Exception("delai ne peux pas etre nulle");
        }
        try {
            double prixdouble=Double.parseDouble((delaiDeLivraison));
            if(prixdouble<0)
            {
                throw new Exception(("valeur ne peux pas etre negative"));
            }
            this.delaiDeLivraison = prixdouble;


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public void setDelaiDeLivraison(double delaiDeLivraison) {
        this.delaiDeLivraison = delaiDeLivraison;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }
    public void setDate(String date) {
        Date = Timestamp.valueOf(date);
    }

    @Override
    public String toString() {
        return "Produit_Fournisseur{" +
                "id=" + id +
                ", fournisseur=" + fournisseur.toString() +
                ", produit=" + produit +
                ", taxe=" + taxe +
                ", delaiDeLivraison=" + delaiDeLivraison +
                ", etat=" + etat +
                ", Date=" + Date +
                ", prixHorsTaxe=" + prixHorsTaxe +
                '}';
    }

    public boolean checkIfNull(){
        boolean resp= true;
        if(id!=null){
            resp=false;
        }if(produit!=null){
            resp=false;
        }if(fournisseur!=null){
            resp=false;
        }if(taxe!=0){
            resp=false;
        }if(prixHorsTaxe!=0){
            resp=false;
        }if(delaiDeLivraison!=0){
            resp=false;
        }if(Date!=null){
            resp=false;
        }
        return resp;
    }

    public List<Produit_Fournisseur> trier(List<Produit_Fournisseur> liste){
        List<Produit_Fournisseur> newList=new ArrayList<>();
        for (int i = 0; i < liste.size(); i++) {
            if(!liste.get(i).checkIfNull()){
                newList.add(liste.get(i));
            }
        }
        return newList;
    }

}
