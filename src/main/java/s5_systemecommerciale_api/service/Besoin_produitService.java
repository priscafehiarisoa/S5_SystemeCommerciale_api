package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.Besoin_produit;
import s5_systemecommerciale_api.repository.Besoin_produitRepository;

import java.util.List;

@Service
public class Besoin_produitService {
    @Autowired
    Besoin_produitRepository besoinProduitRepository;

    public void addNewBesoinProduit(Besoin_produit besoinProduit){
        besoinProduitRepository.save(besoinProduit);
    }

    public List<Besoin_produit> getAllBesoinProduit(){
        return besoinProduitRepository.findAll();
    }

    public void deleteBesoinProduit(long id) throws IllegalAccessException {
        if(!besoinProduitRepository.existsById((long) id))
        {
            throw new IllegalAccessException("besoin produit n existe pas");
        }
        besoinProduitRepository.deleteById((long) id);
    }

}
