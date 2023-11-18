package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Fournisseur;
import s5_systemecommerciale_api.repository.FournisseurRepository;
import s5_systemecommerciale_api.service.FournisseurService;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")

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
    public void insertFournisseur(@RequestBody Fournisseur user)
    {
        System.out.println(user);
        fournisseurService.addNewFournisseur(user);
    }

    @PutMapping("/{id}")
    public Fournisseur updateFournisseur(@RequestBody Fournisseur user, @PathVariable Long id) {
        return fournisseurRepository.findById(Long.valueOf(String.valueOf(id))).map(
                user1 -> {
                    user1.setAdresse(user.getAdresse());
                    user1.setNom_fournisseur(user.getNom_fournisseur());
                    user1.setNom_responsable(user.getNom_responsable());
                    user1.setTelephone(user.getTelephone());
                    user1.setPrix_livraison(user.getPrix_livraison());
                    return fournisseurRepository.save(user1);
                }
        ).orElseGet(() -> {
                    user.setId(id);
                    return fournisseurRepository.save(user);

                }
        );
    }
    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) throws IllegalAccessException {
        fournisseurService.deleteFournisseur(id);
    }

}
