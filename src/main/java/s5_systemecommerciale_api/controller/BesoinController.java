package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Produit;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.service.BesoinService;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")

@RestController
@RequestMapping("/besoin")
public class BesoinController {
    @Autowired
    BesoinRepository besoinRepository;

    @Autowired
    BesoinService besoinService;

    @GetMapping
    public List<Besoin> getAllBesoin()
    {
        return besoinService.getAllBesoin();
    }

    @PostMapping
    public void insert(@RequestBody Besoin besoin){
        besoinService.addNewBesoin(besoin);
    }

    @GetMapping("/deleted")
    public List<Besoin> getAllDeletedBesoin()
    {
        return besoinRepository.getAllDeletedBesoin();
    }
    @GetMapping("/BesoinEnCoursDeValidation")
    public List<Besoin> getAllBesoinEnCoursDeValidation()
    {
        return besoinRepository.getAllBesoinEnCoursDeValidation();
    }
    @GetMapping("/BesoinValide")
    public List<Besoin> getAllBesoinValide()
    {
        return besoinRepository.getAllBesoinValide();
    }

    @PutMapping("/effacer/{id}")
    public  void  deleteBesoin(@PathVariable Long id){
        besoinRepository.deleteBesoin(id);
    }

    @PutMapping("/valider/{id}")
    public  void  validerBesoin(@PathVariable Long id){
        besoinRepository.validerBesoin(id);
    }

}
