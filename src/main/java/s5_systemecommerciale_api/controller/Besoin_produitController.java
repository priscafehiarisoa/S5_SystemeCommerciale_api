package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Besoin_produit;
import s5_systemecommerciale_api.model.Fournisseur;
import s5_systemecommerciale_api.repository.Besoin_produitRepository;
import s5_systemecommerciale_api.service.Besoin_produitService;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")

@RestController
@RequestMapping("/besoinProduit")
public class Besoin_produitController {
    @Autowired
    Besoin_produitRepository besoinProduitRepository;

    @Autowired
    Besoin_produitService besoinProduitService;

    @GetMapping
    public List<Besoin_produit> getAllBesoinProduit(){
        return besoinProduitService.getAllBesoinProduit();
    }

    @PostMapping
    public void insertBesoinProduit(@RequestBody Besoin_produit besoinProduit){
        besoinProduitService.addNewBesoinProduit(besoinProduit);
    }

    @DeleteMapping("/{id}")
    public void deleteBesoinProduit(@PathVariable Long id) throws IllegalAccessException {
        besoinProduitService.deleteBesoinProduit(id);
    }

    @PutMapping("/{id}")
    public Besoin_produit updateBesoinProduit(@RequestBody Besoin_produit user, @PathVariable Long id) {
        return besoinProduitRepository.findById(Long.valueOf(String.valueOf(id))).map(
                user1 -> {
                   user1.setBesoin((user.getBesoin()));
                   user1.setProduit(user.getProduit());
                   user1.setQuantite(user.getQuantite());
                    return besoinProduitRepository.save(user1);
                }
        ).orElseGet(() -> {
                    user.setId(id);
                    return besoinProduitRepository.save(user);

                }
        );
    }

}
