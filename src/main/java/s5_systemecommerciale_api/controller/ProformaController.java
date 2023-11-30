package s5_systemecommerciale_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.besoins.Besoin;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommande;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommandeArticle;
import s5_systemecommerciale_api.model.fournisseur.Produit_Fournisseur;
import s5_systemecommerciale_api.model.produit.Article;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.repository.BonDeCommandeArticleRepository;
import s5_systemecommerciale_api.repository.BonDeCommandeRepository;
import s5_systemecommerciale_api.repository.Produit_fournisseurRepository;
import s5_systemecommerciale_api.service.EmailSenderService;
import s5_systemecommerciale_api.service.Produit_fournisseurService;
import s5_systemecommerciale_api.service.ProformaService;

import java.util.*;

@CrossOrigin()
@RestController
@RequestMapping("/besoin")
public class ProformaController {
    private final ProformaService proformaService;
    private final BesoinRepository besoinRepository;
    private final BonDeCommandeRepository bonDeCommandeRepository;
    private final BonDeCommandeArticleRepository bonDeCommandeArticleRepository;
    private final EmailSenderService emailSenderService;
    private final Produit_fournisseurService produit_fournisseurService;
    private final Produit_fournisseurRepository produit_fournisseurRepository;

    public ProformaController(ProformaService proformaService, BesoinRepository besoinRepository,
                              BonDeCommandeRepository bonDeCommandeRepository,
                              BonDeCommandeArticleRepository bonDeCommandeArticleRepository, EmailSenderService emailSenderService, Produit_fournisseurService produit_fournisseurService, Produit_fournisseurRepository produit_fournisseurRepository) {
        this.proformaService = proformaService;
        this.besoinRepository = besoinRepository;
        this.bonDeCommandeRepository = bonDeCommandeRepository;
        this.bonDeCommandeArticleRepository = bonDeCommandeArticleRepository;
        this.emailSenderService = emailSenderService;
        this.produit_fournisseurService = produit_fournisseurService;
        this.produit_fournisseurRepository = produit_fournisseurRepository;
    }

    // genere automatiquement les bons de commandes
    @GetMapping("getBesoin/{id}")
    @Transactional
    public ResponseEntity<?> getProforma(@PathVariable Long id) throws Exception {
            Optional<Besoin> besoin= besoinRepository.findById(id);
        List<BonsDeCommande> bonsDeCommandes=new ArrayList<>();
        if (besoin.isPresent()) {
            if(!bonDeCommandeRepository.findFirstByBesoin_Id(besoin.get().getId()).isEmpty()){
                bonsDeCommandes=getBonDeCommande(besoin.get());
            }
            else {

                List<List<Article>> prof=new ArrayList<>();

                BonsDeCommande bdc=new BonsDeCommande();

                prof= proformaService.listeArticlesSelonBesoin(besoin.get());
                bonsDeCommandes= bdc.getAllBDC_from_list(prof);

                for (int i = 0; i < bonsDeCommandes.size(); i++) {
                    bonsDeCommandes.get(i).saveBonsDeCommande(bonDeCommandeRepository,bonDeCommandeArticleRepository);
                }
            }
        }
        return new ResponseEntity<>(bonsDeCommandes, HttpStatus.OK);
    }

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

    @PostMapping("/sendEmail")
    public void sendDemandeProforma (@RequestBody Map<String,List<Long>> listes) throws Exception {
        List<Long> listFournisseur= listes.get("fournisseur");
        List<Long> listProduit= listes.get("produit");
        emailSenderService.sendMailToAllFournisseurs(listFournisseur,listProduit);
    }
    @GetMapping("/articles/{id}")
    public List<List<Article>> getarticle(@PathVariable Long id) throws Exception {
        Optional<Besoin> besoin= besoinRepository.findById(id);

        return proformaService.listeArticlesSelonBesoin(besoin.orElseThrow());
    }

    @GetMapping("/moinsDisan/{id}")
    public List<List<Produit_Fournisseur>> getMoinsDisant(@PathVariable  Long id){
        Optional<Besoin> besoin= besoinRepository.findById(id);
        return produit_fournisseurService.getNmeilleurPrix(besoin.get(),3);
    }

    @GetMapping("/getPF")
    public List<List<Produit_Fournisseur>> getPF(){
        List<List<Produit_Fournisseur>> nf=new ArrayList<>();
        nf.add(produit_fournisseurRepository.getAllValidateproduit());
        return nf;
    }
}
