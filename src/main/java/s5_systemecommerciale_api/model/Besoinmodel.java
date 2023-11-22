package s5_systemecommerciale_api.model;

import s5_systemecommerciale_api.repository.Besoin_produitRepository;

import java.util.List;

public class Besoinmodel {
    Besoin besoin;
    List<Besoin_produit> listeBesoin;

    public Besoin getBesoin() {
        return besoin;
    }

    public void setBesoin(Besoin besoin) {
        this.besoin = besoin;
    }

    public List<Besoin_produit> getListeBesoin() {
        return listeBesoin;
    }

    public void setListeBesoin(List<Besoin_produit> listeBesoin) {
        this.listeBesoin = listeBesoin;
    }

    public Besoinmodel(Besoin besoin, Besoin_produitRepository besoin_produitRepository) {
        setBesoin(besoin);
        setListeBesoin(besoin_produitRepository.findAllByBesoin_Id(getBesoin().getId()));
    }
}
