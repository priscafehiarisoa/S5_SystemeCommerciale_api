package s5_systemecommerciale_api.service;

import org.hibernate.query.spi.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Fournisseur;
import s5_systemecommerciale_api.model.Produit;
import s5_systemecommerciale_api.model.Produit_Fournisseur;
import s5_systemecommerciale_api.repository.FournisseurRepository;
import s5_systemecommerciale_api.repository.ProduitRepository;
import s5_systemecommerciale_api.repository.Produit_fournisseurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Produit_fournisseurService {

    @Autowired
    Produit_fournisseurRepository produitFournisseurRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private ProduitRepository produitRepository;

    public void AddnewProduitFournisseur(Produit_Fournisseur produitFournisseur){
        // verifier si on possède déjà un produit du fournisseur dans la base de donnée
        Optional<Produit_Fournisseur> pf= produitFournisseurRepository.getValideProduitFournisseurByIdProduit(produitFournisseur.getProduit().getId(),produitFournisseur.getFournisseur().getId());
        Optional <Fournisseur> fournisseur= fournisseurRepository.findById(produitFournisseur.getFournisseur().getId());
        Optional <Produit> produit= produitRepository.findById(produitFournisseur.getProduit().getId());
        // update le pf s'il existe
        if(pf.isPresent()){
            Produit_Fournisseur pf2= pf.get();
            produitFournisseurRepository.makeProduitFournisseurUnavailable(pf2.getId());
        }
        Produit_Fournisseur prod= new Produit_Fournisseur(fournisseur.get(), produit.get(), produitFournisseur.getTaxe(), produitFournisseur.getDelaiDeLivraison(), produitFournisseur.getEtat(), produitFournisseur.getDate(),produitFournisseur.getPrixHorsTaxe());
        produitFournisseurRepository.save(prod);
    }

    // obtenir les 3 meilleurs prix a partir des prix qu'on a dans la base
    public List<List<Produit_Fournisseur>> get3meilleurPrix(Besoin besoin){
        List<List<Produit_Fournisseur>> response = new ArrayList<>();
        for (int i = 0; i < besoin.getBesoinProduits().size(); i++) {
            Optional<Produit> produit= produitRepository.findById(besoin.getBesoinProduits().get(i).getProduit().getId());
            if(produit.isPresent()){
                List<Produit_Fournisseur> liste= produitFournisseurRepository.getMini_proforma(produit.get());
                response.add(liste);
            }
        }
        return response;
    }

    // obtenir les n meilleurs prix a partir des prix qu'on a dans la base
    public List<List<Produit_Fournisseur>> getNmeilleurPrix(Besoin besoin,int limit){
        List<List<Produit_Fournisseur>> response = new ArrayList<>();
        for (int i = 0; i < besoin.getBesoinProduits().size(); i++) {
            Optional<Produit> produit= produitRepository.findById(besoin.getBesoinProduits().get(i).getProduit().getId());
            if(produit.isPresent()){
                Pageable pages = PageRequest.of(0, limit);

                List<Produit_Fournisseur> liste= produitFournisseurRepository.get_n_Mini_proforma(produit.get(),pages);
                response.add(liste);
            }
        }
        return response;
    }

}
