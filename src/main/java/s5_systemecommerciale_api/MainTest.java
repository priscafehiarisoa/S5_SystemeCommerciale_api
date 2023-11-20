package s5_systemecommerciale_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s5_systemecommerciale_api.model.*;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.repository.FournisseurRepository;
import s5_systemecommerciale_api.repository.ProduitRepository;
import s5_systemecommerciale_api.repository.ServiceRepository;
import s5_systemecommerciale_api.service.Produit_fournisseurService;
import s5_systemecommerciale_api.service.ProformaService;

import java.util.List;
import java.util.Optional;

@Configuration
public class MainTest {
    private final FournisseurRepository fournisseurRepository;
    private final ServiceRepository serviceRepository;

    public MainTest(FournisseurRepository fournisseurRepository, ServiceRepository serviceRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.serviceRepository = serviceRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(BesoinRepository besoinRepository,ProduitRepository produitRepository, Produit_fournisseurService produitFournisseurService, ProformaService proformaService){
        return args -> {
            Optional<Besoin> besoin= besoinRepository.findById(8L);
            Produit produit = new Produit("ordi portable",0);
            Produit produit2 = new Produit("produit 2",0);
            Produit produit3 = new Produit("ordi portable 4",0);
            Produit produit4 = new Produit("ordi portable 5",0);
            produitRepository.saveAll(List.of(produit,produit2,produit3,produit4));
            Fournisseur fournisseur= new Fournisseur("Jeddy","andoram","0341872531","jeddy2",2000);
            fournisseurRepository.save(fournisseur);

            Service service = new Service("service1");
            Service service2 = new Service("service2");
            serviceRepository.saveAll(List.of(service2,service));
//            System.out.println(prof.toString());
        };
    }
}
