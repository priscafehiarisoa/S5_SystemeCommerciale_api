package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import s5_systemecommerciale_api.model.besoins.Besoin;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.service.BesoinService;

import java.time.Duration;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/besoin")
public class BesoinController {
    @Autowired
    BesoinRepository besoinRepository;

    @Autowired
    BesoinService besoinService;

//    @PersistenceContext
//    private EntityManager entityManager;
    private final RestTemplate restTemplate;


    public BesoinController(RestTemplateBuilder restTemplateBuilder) {
       this.restTemplate =new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();
    }

//    @GetMapping
//    @Transactional
//
//    public List<List <Besoin_produit> > getAllBesoin()
//    {
//        List<List <Besoin_produit> > bp=besoinService.getAllBesoin();
//        System.out.println("===============3=========");
//        System.out.println(bp.toString());
//        return bp;
//    }
    @GetMapping
    @Transactional

    public List<Besoin> getAllBesoin()
    {

        return besoinService.getAllBesoin();
    }



    @PostMapping
    @Transactional
    public void insert(@RequestBody Besoin besoin) throws Exception {
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

    @DeleteMapping("/effacer/{id}")
    public  void  deleteBesoin(@PathVariable Long id){
        besoinRepository.deleteBesoin(id);
    }

    @PutMapping("/valider1/{id}")
    public  void  validerBesoin(@PathVariable Long id){
        besoinRepository.validerBesoin1(id);
    }
    @PutMapping("/valider2/{id}")
    public  void  validerBesoin2(@PathVariable Long id){
        besoinRepository.validerBesoin2(id);
    }

}
