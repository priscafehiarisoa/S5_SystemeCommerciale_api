package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.produit.Produit;
import s5_systemecommerciale_api.repository.ProduitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;

    public void addNewProduit(Produit produit){
        produitRepository.save(produit);
    }

    public void deleteProduit(long id) throws IllegalAccessException {
        if(!produitRepository.existsById((long) id))
        {
            throw new IllegalAccessException("produit n existe pas");
        }
        produitRepository.deleteById((long) id);
    }
    public List<Produit> getListProduitByIDs(List<Long> ids) throws Exception {
        List<Produit> produits= new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Optional<Produit> produit= produitRepository.findById(ids.get(i));
            if(produit.isPresent()){
                produits.add(produit.get());
            }
            else{
                throw new Exception("produit n* : "+ ids.get(i)+" introuvalble");
            }
        }
        return produits;
    }
}
