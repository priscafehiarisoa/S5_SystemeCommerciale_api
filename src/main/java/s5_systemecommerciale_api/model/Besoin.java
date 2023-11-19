package s5_systemecommerciale_api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Besoin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="besoin_seq" )
    @SequenceGenerator(name = "besoin_seq" , sequenceName = "besoin_seq", allocationSize = 1)
    @Column(name="id_besoin")
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_service")
    Service service;
    double quantite;
    String motif;
    @OneToMany(mappedBy = "besoin", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Besoin_produit> besoinProduits;
    int etat=0;

    public Besoin() {
    }

    public Besoin(Service service, double quantite, String motif, int etat) {
        setService(service);
        setMotif(motif);
        setQuantite(quantite);
        setEtat(etat);
    }

    public Besoin(Long id, Service service, double quantite, String motif, int etat) {
        setId(id);
        setService(service);
        setMotif(motif);
        setQuantite(quantite);
        setEtat(etat);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getEtat() {
        return etat;
    }

    public List<Besoin_produit> getBesoinProduits() {
        return besoinProduits;
    }

    public void setBesoinProduits(List<Besoin_produit> besoinProduits) {
        this.besoinProduits = besoinProduits;
    }

    public void setEtat(int etat) {
        this.etat = etat;
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
