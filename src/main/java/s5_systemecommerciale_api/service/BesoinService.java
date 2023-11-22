package s5_systemecommerciale_api.service;

import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Besoin_produit;
import s5_systemecommerciale_api.model.Besoinmodel;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.repository.Besoin_produitRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BesoinService {
    @Autowired
    BesoinRepository besoinRepository;
    @Autowired
    private Besoin_produitRepository besoin_produitRepository;


    @Transactional
    public List<List <Besoin_produit> > getAllBesoin(){
        List<Besoin> lb= besoinRepository.getAllBesoinEnCoursDeValidation();
        List<Besoinmodel> besoinmodels = new ArrayList<>();
        List<List <Besoin_produit> >besoinProduits=new ArrayList<>();
        for (int i = 0; i < lb.size(); i++) {
            Besoinmodel bm=(new Besoinmodel(lb.get(i),besoin_produitRepository));
            besoinProduits.add(bm.getListeBesoin());
        }
        return besoinProduits;
    }
//    public String getAllBesoin2(){
//        List<Besoin> lb= besoinRepository.getAllBesoinEnCoursDeValidation();
//        for (int i = 0; i < lb.size(); i++) {
//            lb.get(i).setBesoinProduits(besoin_produitRepository.findAllByBesoin_Id(lb.get(i).getId()));
//        }
//        Gson g= new Gson();
//        return g.toJson(lb);
//    }

    public void addNewBesoin(Besoin besoin) throws Exception {
        // nouveau besoin
        Besoin newBesoin = new Besoin(besoin.getService(), besoin.getMotif(), besoin.getEtat());

        // recreer une liste de besoin_produit avec l'id de newbesoin
        List<Besoin_produit> besoinProduits=new ArrayList<>();
        for (int i = 0; i < besoin.getBesoinProduits().size(); i++) {
            Besoin_produit bp= new Besoin_produit(besoin.getBesoinProduits().get(i).getQuantite(),newBesoin,besoin.getBesoinProduits().get(i).getProduit());
            besoin_produitRepository.save(bp);
            besoinProduits.add(bp);
        }
        newBesoin.setBesoinProduits(besoinProduits);
        besoinRepository.save(newBesoin);

    }


}
