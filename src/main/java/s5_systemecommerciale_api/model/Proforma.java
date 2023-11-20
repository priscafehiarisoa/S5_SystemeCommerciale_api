package s5_systemecommerciale_api.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

public class Proforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany
    List<Produit_Fournisseur> produit_fournisseurList;
    double totalPrixHt;
    double totalPrixTTC;
     List<Besoin_produit> besoinProduits;

    public List<Besoin_produit> getBesoinProduits() {
        return besoinProduits;
    }

    public void setBesoinProduits(List<Besoin_produit> besoinProduits) {
        this.besoinProduits = besoinProduits;
    }

    public Long getId() {
        return id;
    }



    public List<Produit_Fournisseur> getProduit_fournisseurList() {
        return produit_fournisseurList;
    }

    public double getTotalPrixHt() {
        return totalPrixHt;
    }

    public double getTotalPrixTTC() {
        return totalPrixTTC;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Proforma() {
    }


//    public void setBesoin(Besoin besoin) {
//        this.besoin = besoin;
//    }

    public void setProduit_fournisseurList(List<Produit_Fournisseur> produit_fournisseurList) {
        this.produit_fournisseurList = produit_fournisseurList;
    }

    public void setTotalPrixHt(double totalPrixHt) {
        this.totalPrixHt = totalPrixHt;
    }
//    todo ++++++++++++++++++++++++++
    public void setTotalPrixHt() {
        if(!getProduit_fournisseurList().isEmpty() && !getBesoinProduits().isEmpty()){
            double prixHt= 0 ;
            for (int i = 0; i < getBesoinProduits().size(); i++) {
                Besoin_produit bp=getBesoinProduits().get(i);
                for (int j = 0; j < getProduit_fournisseurList().size(); j++) {
                    Produit_Fournisseur pf=getProduit_fournisseurList().get(j);
                    if(bp.getProduit()==pf.getProduit()){
                        prixHt+=bp.getQuantite()*pf.getPrixHorsTaxe();
                    }
                }
            }
            setTotalPrixHt(prixHt);
        }
    }

    public void setTotalPrixTTC(double totalPrixTTC) {
        this.totalPrixTTC = totalPrixTTC;
    }

    public Proforma(Besoin besoin, List<Produit_Fournisseur> produit_fournisseurList, double totalPrixHt, double totalPrixTTC) {
        setBesoinProduits(besoin.getBesoinProduits());
        setProduit_fournisseurList(produit_fournisseurList);
        setTotalPrixHt(totalPrixHt);
        setTotalPrixTTC(totalPrixTTC);
    }

//    @Override
//    public String toString() {
//        return "Proforma{" +
//                "besoin=" + besoinProduits.toString() +
//                ", produit_fournisseurList=" + produit_fournisseurList.toString() +
//                ", totalPrixHt=" + totalPrixHt +
//                ", totalPrixTTC=" + totalPrixTTC +
//                '}';
//    }

    public Proforma(Long id, Besoin besoin, List<Produit_Fournisseur> produit_fournisseurList, double totalPrixHt, double totalPrixTTC) {
        this.id = id;
        this.besoinProduits = besoin.getBesoinProduits();
        this.produit_fournisseurList = produit_fournisseurList;
        this.totalPrixHt = totalPrixHt;
        this.totalPrixTTC = totalPrixTTC;
    }
}
