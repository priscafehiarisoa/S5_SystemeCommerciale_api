package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.Produit_Fournisseur;
import s5_systemecommerciale_api.repository.Produit_fournisseurRepository;

import java.util.List;

@Service
public class Produit_fournisseurService {

    @Autowired
    Produit_fournisseurRepository produitFournisseurRepository;

    public void AddnewProduitFournisseur(Produit_Fournisseur produitFournisseur){
        produitFournisseurRepository.save(produitFournisseur);
    }

}
