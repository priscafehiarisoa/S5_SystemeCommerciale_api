package s5_systemecommerciale_api.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Besoin_produit;
import s5_systemecommerciale_api.repository.BesoinRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BesoinService {
    @Autowired
    BesoinRepository besoinRepository;


    public List<Besoin> getAllBesoin(){
        return besoinRepository.findAll();
    }

    public void addNewBesoin(Besoin besoin) throws Exception {
        // nouveau besoin
        Besoin newBesoin = new Besoin(besoin.getService(), besoin.getQuantite(), besoin.getMotif(), besoin.getEtat());
        // recreer une liste de besoin_produit avec l'id de newbesoin
        List<Besoin_produit> besoinProduits=new ArrayList<>();
        for (int i = 0; i < besoin.getBesoinProduits().size(); i++) {
            Besoin_produit bp= new Besoin_produit(besoin.getBesoinProduits().get(i).getQuantite(),newBesoin,besoin.getBesoinProduits().get(i).getProduit());
            besoinProduits.add(bp);
        }
        newBesoin.setBesoinProduits(besoinProduits);
        besoinRepository.save(newBesoin);

    }


}
