package s5_systemecommerciale_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.*;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.repository.BonDeCommandeArticleRepository;
import s5_systemecommerciale_api.repository.BonDeCommandeRepository;
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

    public ProformaController(ProformaService proformaService, BesoinRepository besoinRepository,
                              BonDeCommandeRepository bonDeCommandeRepository,
                              BonDeCommandeArticleRepository bonDeCommandeArticleRepository) {
        this.proformaService = proformaService;
        this.besoinRepository = besoinRepository;
        this.bonDeCommandeRepository = bonDeCommandeRepository;
        this.bonDeCommandeArticleRepository = bonDeCommandeArticleRepository;
    }

    @GetMapping("getBesoin/{id}")
    @Transactional
    public ResponseEntity<?> getProforma(@PathVariable Long id){
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

    public List<BonsDeCommande> getAndSaveBonDeCommande(Besoin besoin){
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
}
