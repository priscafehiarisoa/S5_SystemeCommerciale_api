package s5_systemecommerciale_api.model;

public class Article {
    String designation;
    double quantite;
    double prixHorsTaxe;
    double taxes;
    double prixTTC;
    double PrixUnitaire;
    Fournisseur fournisseur;
    Produit produit;

    public Article( Besoin_produit besoinProduit, Produit_Fournisseur produitFournisseur) {
        setDesignation(besoinProduit.getProduit().nomProduit);
        setPrixUnitaire(produitFournisseur.getPrixHorsTaxe());
        setQuantite(besoinProduit.getQuantite());
        setTaxes(produitFournisseur.getTaxe());
        setPrixHorsTaxe(getQuantite()*getPrixUnitaire());
        setPrixTTC(getPrixHorsTaxe()+(getPrixHorsTaxe()*(taxes/100)));
        setFournisseur(produitFournisseur.getFournisseur());
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public double getPrixUnitaire() {
        return PrixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        PrixUnitaire = prixUnitaire;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrixHorsTaxe() {
        return prixHorsTaxe;
    }

    public void setPrixHorsTaxe(double prixHorsTaxe) {
        this.prixHorsTaxe = prixHorsTaxe;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(double prixTTC) {
        this.prixTTC = prixTTC;
    }


}
