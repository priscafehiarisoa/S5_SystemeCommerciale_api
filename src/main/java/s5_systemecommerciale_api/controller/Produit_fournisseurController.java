package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Produit_Fournisseur;
import s5_systemecommerciale_api.repository.ProduitRepository;
import s5_systemecommerciale_api.repository.Produit_fournisseurRepository;
import s5_systemecommerciale_api.service.ProduitService;
import s5_systemecommerciale_api.service.Produit_fournisseurService;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")

@RestController
@RequestMapping("/produitFournisseur")
public class Produit_fournisseurController {

    @Autowired
    Produit_fournisseurService produitFournisseurService;
    @Autowired
    Produit_fournisseurRepository produitFournisseurRepository;
    @GetMapping
    public List<Produit_Fournisseur> getAllValidateProduitFournisseur(){
        return produitFournisseurRepository.getAllValidateproduit();
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
