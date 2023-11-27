package s5_systemecommerciale_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import s5_systemecommerciale_api.model.fournisseur.Produit_Fournisseur;
import s5_systemecommerciale_api.service.files.ExelReadingService;

import java.util.List;

@RestController public class Controller {
    private final ExelReadingService exelReadingService;

    public Controller(ExelReadingService exelReadingService) {
        this.exelReadingService = exelReadingService;
    }


}
