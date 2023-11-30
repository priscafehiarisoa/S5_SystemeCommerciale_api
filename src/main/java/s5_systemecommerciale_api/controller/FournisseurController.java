package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.fournisseur.Fournisseur;
import s5_systemecommerciale_api.repository.FournisseurRepository;
import s5_systemecommerciale_api.service.FournisseurService;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/fournisseur")
public class FournisseurController {

    @Autowired
    FournisseurRepository fournisseurRepository;

    @Autowired
    FournisseurService fournisseurService;

    @GetMapping
    public List<Fournisseur> getAllFournisseur(){
        return fournisseurService.getAllFournisseur();
    }


    @PostMapping
    public void insertFournisseur(@RequestBody Fournisseur fournisseur) throws Exception {
            fournisseurService.addNewFournisseur(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur updateFournisseur(@RequestBody Fournisseur fournisseur, @PathVariable Long id) {
        return fournisseurRepository.findById(Long.valueOf(String.valueOf(id))).map(
                fournisseur1 -> {
                    fournisseur1.setAdresse(fournisseur.getAdresse());
                    fournisseur1.setNom_fournisseur(fournisseur.getNom_fournisseur());
                    fournisseur1.setNom_responsable(fournisseur.getNom_responsable());
                    fournisseur1.setTelephone(fournisseur.getTelephone());
                    fournisseur1.setPrix_livraison(fournisseur.getPrix_livraison());
                    return fournisseurRepository.save(fournisseur1);
                }
        ).orElseGet(() -> {
            fournisseur.setId(id);
                    return fournisseurRepository.save(fournisseur);

                }
        );
    }
    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) throws IllegalAccessException {
        fournisseurService.deleteFournisseur(id);
    }

}
