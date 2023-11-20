package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Produit_Fournisseur;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.repository.ProduitRepository;
import s5_systemecommerciale_api.repository.Produit_fournisseurRepository;
import s5_systemecommerciale_api.service.ProduitService;
import s5_systemecommerciale_api.service.Produit_fournisseurService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")

@RestController
@RequestMapping("/produitFournisseur")
public class Produit_fournisseurController {

    @Autowired
    Produit_fournisseurService produitFournisseurService;
    @Autowired
    Produit_fournisseurRepository produitFournisseurRepository;
    @Autowired
    private BesoinRepository besoinRepository;

    @GetMapping
    public List<Produit_Fournisseur> getAllValidateProduitFournisseur(){
        return produitFournisseurRepository.getAllValidateproduit();
    }

    @GetMapping("/Proforma_3_fournisseur/{idBesoin}")
    public List<List<Produit_Fournisseur>> getProforma_3_fournisseur(@PathVariable Long idBesoin){
        Optional<Besoin> besoin= besoinRepository.findById(idBesoin);
        List<List<Produit_Fournisseur>> lp=new ArrayList<>();
        if(besoin.isPresent()){
           lp = produitFournisseurService.get3meilleurPrix(besoin.get());

        }
        return lp;
    }

    @PostMapping
    public void insert(@RequestBody Produit_Fournisseur produitFournisseur){
        produitFournisseurService.AddnewProduitFournisseur(produitFournisseur);
    }

    @PutMapping("/effacer/{id}")
    public  void  deleteProduitFournisseur(@PathVariable Long id){
        produitFournisseurRepository.deleteproduitFournisseur(id);
    }
}
