package s5_systemecommerciale_api.controller.mail;

import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.fournisseur.Produit_Fournisseur;
import s5_systemecommerciale_api.model.mail.MailStructure;
import s5_systemecommerciale_api.service.files.ExelReadingService;

import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final ExelReadingService exelReadingService;

    public MailController(ExelReadingService exelReadingService) {
        this.exelReadingService = exelReadingService;
    }



}
