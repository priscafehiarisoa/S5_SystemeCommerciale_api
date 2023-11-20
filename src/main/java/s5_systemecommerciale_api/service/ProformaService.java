package s5_systemecommerciale_api.service;

import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Produit_Fournisseur;
import s5_systemecommerciale_api.model.Proforma;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProformaService {
    private final Produit_fournisseurService produit_fournisseurService;

    public ProformaService(Produit_fournisseurService produit_fournisseurService) {
        this.produit_fournisseurService = produit_fournisseurService;
    }

    public Proforma getProforma(Besoin besoin ){
        Proforma proforma= new Proforma();
        proforma.setBesoinProduits(besoin.getBesoinProduits());
        System.out.println("tyyy +++ ?? "+ besoin.getBesoinProduits().toString());
        List<List<Produit_Fournisseur>> pf = produit_fournisseurService.getNmeilleurPrix(besoin,1);
        List<Produit_Fournisseur> produitFournisseurs=new ArrayList<>();
        for (int i = 0; i < pf.size(); i++) {
            produitFournisseurs.addAll(pf.get(i));
        }
        proforma.setProduit_fournisseurList(produitFournisseurs);
        proforma.setTotalPrixHt();
        return proforma;

    }
}
