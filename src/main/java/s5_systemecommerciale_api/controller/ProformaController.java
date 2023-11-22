package s5_systemecommerciale_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Article;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Proforma;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.service.ProformaService;

import java.util.*;

@CrossOrigin()
@RestController
@RequestMapping("/besoin")
public class ProformaController {
    private final ProformaService proformaService;
    private final BesoinRepository besoinRepository;

    public ProformaController(ProformaService proformaService, BesoinRepository besoinRepository) {
        this.proformaService = proformaService;
        this.besoinRepository = besoinRepository;
    }

    @GetMapping("getBesoin/{id}")
    @Transactional
    public ResponseEntity<?> getProforma(@PathVariable Long id){
            Optional<Besoin> besoin= besoinRepository.findById(id);
        List<List<Article>> prof=new ArrayList<>();
            if (besoin.isPresent()) {
//
               prof= proformaService.listeArticlesSelonBesoin(besoin.get());
            }


        return new ResponseEntity<>(prof, HttpStatus.OK);
    }
}
