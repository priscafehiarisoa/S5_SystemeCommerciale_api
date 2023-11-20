package s5_systemecommerciale_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Produit_Fournisseur;
import s5_systemecommerciale_api.model.Proforma;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.service.Produit_fournisseurService;
import s5_systemecommerciale_api.service.ProformaService;

import java.util.List;
import java.util.Optional;

@Configuration
public class MainTest {
    @Bean
    CommandLineRunner commandLineRunner(BesoinRepository besoinRepository, Produit_fournisseurService produitFournisseurService, ProformaService proformaService){
        return args -> {
            Optional<Besoin> besoin= besoinRepository.findById(8L);
            Proforma prof=new Proforma();
            if (besoin.isPresent()) {
                for (int i = 0; i < besoin.get().getBesoinProduits().size(); i++) {
                    System.out.println(i);
                }
               prof=proformaService.getProforma(besoin.get());
            }
            System.out.println(prof.toString());
        };
    }
}
