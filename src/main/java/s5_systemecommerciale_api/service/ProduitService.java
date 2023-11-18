package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.Produit;
import s5_systemecommerciale_api.repository.ProduitRepository;

import java.util.List;

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
}
