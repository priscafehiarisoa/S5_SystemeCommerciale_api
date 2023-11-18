package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Produit;
import s5_systemecommerciale_api.repository.ProduitRepository;
import s5_systemecommerciale_api.service.ProduitService;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")

@RestController
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    ProduitService produitService;

    @GetMapping
    public List<Produit> getAllproduit()
    {
        return produitRepository.getAllproduit();
    }
    @GetMapping("/deleted")
    public List<Produit> getAllDeletedproduit()
    {
        return produitRepository.getAllDeletedproduit();
    }

    @PostMapping
    public void insert(@RequestBody Produit produit){
        produitService.addNewProduit(produit);
    }

    @PutMapping("/effacer/{id}")
    public  void  deleteProduit(@PathVariable Long id){
        produitRepository.deleteproduit(id);
    }

    @PutMapping("/{id}")
    public Produit updateFournisseur(@RequestBody Produit user, @PathVariable Long id) {
        return produitRepository.findById(Long.valueOf(String.valueOf(id))).map(
                user1 -> {
                  user1.setEtat(user.getEtat());
                  user1.setNomProduit(user.getNomProduit());
                    return produitRepository.save(user1);
                }
        ).orElseGet(() -> {
                    user.setId(id);
                    return produitRepository.save(user);

                }
        );
    }
}

