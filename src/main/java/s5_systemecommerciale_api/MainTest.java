package s5_systemecommerciale_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;
import s5_systemecommerciale_api.repository.*;
import s5_systemecommerciale_api.service.EmailSenderService;
import s5_systemecommerciale_api.service.Produit_fournisseurService;
import s5_systemecommerciale_api.service.ProformaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class MainTest {
    @Bean
    CommandLineRunner commandLineRunner(BesoinRepository besoinRepository, Produit_fournisseurService produitFournisseurService, ProformaService proformaService,
                                        Besoin_produitRepository besoin_produitRepository,
                                        BonDeCommandeRepository bonDeCommandeRepository,
                                        BonDeCommandeArticleRepository bonDeCommandeArticleRepository,
                                        EmailSenderService emailSenderService,
                                        EntrepriseInformationRepository entrepriseInformationRepository

    ){
        return args -> {
            //Entreprise informations
            EntrepriseInformation e= new EntrepriseInformation(1L,"MYTY","mytyrealy@Gmail.com","0347067949", "Lot III E 132 CT Fort Voyron");
            entrepriseInformationRepository.save(e);

            //end
//            Optional<Besoin> besoin= besoinRepository.findById(11L);
//            Proforma prof=new Proforma();
//            if (besoin.isPresent()) {
//                System.out.println("**************");
//                List<List<Article> >liste=proformaService.listeArticlesSelonBesoin(besoin.get());
//                for (int i = 0; i < liste.size(); i++) {
//                    System.out.println(liste.get(i).toString());
//                }
////                List<Besoin_produit> bp=besoin_produitRepository.findAllByBesoin_Id(besoin.get().getId());
////                bp.forEach(System.out::println);
////                System.out.println(bp.size());
//
//                System.out.println("===****==== "+bonDeCommandeRepository.findFirstByBesoin_Id(12L));
//                System.out.println("===2===="+bonDeCommandeRepository.findAllByBesoin_Id(12L).size());
//                List<BonsDeCommande> bonsDeCommandes=bonDeCommandeRepository.findAllByBesoin_Id(12L);
//                for (int b = 0; b < bonsDeCommandes.size(); b++) {
//                    System.out.println("===3===="+bonDeCommandeArticleRepository.findAllByIdBonDeCommande(bonsDeCommandes.get(b).getId_bonsDeCommande()).size());
//
//                }
//            }
            List<Long> fournisseur= new ArrayList<>();
            fournisseur.add(1L);
            fournisseur.add(2L);
            fournisseur.add(3L);
            List<Long> produit=new ArrayList<>();
            produit.add(1L);
            produit.add(2L);
            produit.add(3L);
            emailSenderService.sendMailToAllFournisseurs(fournisseur,produit);
//            emailSenderService.sendMailWithAttachement("mytyrealy@gmail.com","mety le izyyy ", "kikouuuu","/Users/priscafehiarisoadama/Desktop/ville.shp");

        };
    }
}
