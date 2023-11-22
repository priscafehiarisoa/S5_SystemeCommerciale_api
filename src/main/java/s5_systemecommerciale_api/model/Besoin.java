package s5_systemecommerciale_api.model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Arrays;
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
    String motif;
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "besoin", cascade = CascadeType.PERSIST)
    @Transient
    List<Besoin_produit> besoinProduits;
    int etat=0;

    public Besoin() {
    }

    public Besoin(Service service,  String motif, int etat) {
        setService(service);
        setMotif(motif);
        setEtat(etat);
    }

    public Besoin(Long id, Service service,  String motif, int etat) {
        setId(id);
        setService(service);
        setMotif(motif);
        setEtat(etat);
    }
    public Besoin(Long id, Service service, String motif, int etat,List<Besoin_produit> besoinProduits) {
        setId(id);
        setService(service);
        setMotif(motif);
        setEtat(etat);
        setBesoinProduits(besoinProduits);
    }

    public List<Besoin_produit> getBesoinProduits() {
        return besoinProduits;
    }

    public void setBesoinProduits(List<Besoin_produit> besoinProduits) {
        this.besoinProduits = besoinProduits;
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



    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }


    @Override
    public String toString() {
//        Hibernate.initialize(besoinProduits);

        return "Besoin{" +
                "id=" + id +
                ",\n service=" + service.toString() +
                ",\n motif='" + motif + '\'' +
                ",\n etat=" + etat +
//                "besoinProduits="+ getBesoinProduits()+
                '}';
    }
}
