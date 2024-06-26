package s5_systemecommerciale_api.service;

import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.produit.Article;
import s5_systemecommerciale_api.model.besoins.Besoin;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommande;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommandeArticle;
import s5_systemecommerciale_api.repository.BonDeCommandeArticleRepository;
import s5_systemecommerciale_api.repository.BonDeCommandeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BonsDeCommandeService {

    private final BonDeCommandeRepository bonDeCommandeRepository;
    private final BonDeCommandeArticleRepository bonDeCommandeArticleRepository;
    private final ProformaService proformaService;

    public BonsDeCommandeService(BonDeCommandeRepository bonDeCommandeRepository,
                                 BonDeCommandeArticleRepository bonDeCommandeArticleRepository, ProformaService proformaService) {
        this.bonDeCommandeRepository = bonDeCommandeRepository;
        this.bonDeCommandeArticleRepository = bonDeCommandeArticleRepository;
        this.proformaService = proformaService;
    }

    // creer un bons de commande

    public List<BonsDeCommande> getAndSaveBonDeCommande(Besoin besoin) throws Exception {
        List<BonsDeCommande> bonsDeCommandes=new ArrayList<>();

        List<List<Article>> prof=new ArrayList<>();

        BonsDeCommande bdc=new BonsDeCommande();

        prof= proformaService.listeArticlesSelonBesoin(besoin);
        bonsDeCommandes= bdc.getAllBDC_from_list(prof);

        for (int i = 0; i < bonsDeCommandes.size(); i++) {
            bonsDeCommandes.get(i).saveBonsDeCommande(bonDeCommandeRepository,bonDeCommandeArticleRepository);
        }
        return bonsDeCommandes;
    }

    public List<BonsDeCommande> getBonDeCommande(Besoin besoin){
        List<BonsDeCommande> bonsDeCommandes= bonDeCommandeRepository.findAllByBesoin_Id(besoin.getId());
        for (int i = 0; i < bonsDeCommandes.size(); i++) {
            List<BonsDeCommandeArticle> bonsDeCommandeArticles=bonDeCommandeArticleRepository.findAllByIdBonDeCommande(bonsDeCommandes.get(i).getId_bonsDeCommande());
            bonsDeCommandes.get(i).setListeArticles(bonsDeCommandeArticles);
        }
        return bonsDeCommandes;
    }

    //verification si un bons de commande spécifique Existe

    public boolean checkExistenceBonDeCommande(Long id){
        Optional<BonsDeCommande> bdc= bonDeCommandeRepository.findById(id);
        return bdc.isPresent();
    }

}
