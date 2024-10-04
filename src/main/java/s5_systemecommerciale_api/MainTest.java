package s5_systemecommerciale_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s5_systemecommerciale_api.model.Factures.FactureFournisseur;
import s5_systemecommerciale_api.model.elses.Service;
import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;
import s5_systemecommerciale_api.model.fournisseur.Fournisseur;
import s5_systemecommerciale_api.model.fournisseur.Produit_Fournisseur;
import s5_systemecommerciale_api.model.produit.Produit;
import s5_systemecommerciale_api.model.stock.SortieStock;
import s5_systemecommerciale_api.model.stock.VStock;
import s5_systemecommerciale_api.model.user.Users;
import s5_systemecommerciale_api.repository.*;
import s5_systemecommerciale_api.repository.user.UserRepository;
import s5_systemecommerciale_api.service.EmailSenderService;
import s5_systemecommerciale_api.service.Produit_fournisseurService;
import s5_systemecommerciale_api.service.ProformaService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class MainTest {
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final FournisseurRepository fournisseurRepository;
    private final FactureFournisseurRepository factureFournisseurRepository;

    public MainTest(ServiceRepository serviceRepository, UserRepository userRepository, FournisseurRepository fournisseurRepository, FactureFournisseurRepository factureFournisseurRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.factureFournisseurRepository = factureFournisseurRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(BesoinRepository besoinRepository, Produit_fournisseurService produitFournisseurService, ProformaService proformaService,
                                        Besoin_produitRepository besoin_produitRepository,
                                        BonDeCommandeRepository bonDeCommandeRepository,
                                        BonDeCommandeArticleRepository bonDeCommandeArticleRepository,
                                        EmailSenderService emailSenderService,
                                        EntrepriseInformationRepository entrepriseInformationRepository,

                                        ProduitRepository produitRepository,
                                        Produit_fournisseurRepository produit_fournisseurRepository,
                                        VStockRepository vStockRepository){
        return args -> {
            // create services

            List<Service> services=new ArrayList<>();
            services.add(new Service(1L,"IT"));
            services.add(new Service(2L,"Finances"));
            services.add(new Service(3L,"Achat"));
            services.add(new Service(4L,"DAF"));


            serviceRepository.saveAll(services);

            // create users
            List<Users> usersList=new ArrayList<>();
            usersList.add(new Users(2L,"Rakotoniary Nirina","nirina@gmail.com" ,"123","responsable",services.get(0)));
            usersList.add(new Users(3L,"Manohy Arivelo","manohy@gmail.com" ,"123","responsable",services.get(0)));
            usersList.add(new Users(4L,"Aina malala","Aina@gmail.com" ,"123","responsable",services.get(1)));
            usersList.add(new Users(5L,"Lahatra Tiana","lahatra@gmail.com" ,"123","employe",services.get(1)));
            usersList.add(new Users(6L,"Tafita Herry","tafita@gmail.com" ,"123","responsable",services.get(2)));
            usersList.add(new Users(7L,"Ravo Hary Rakotondrabary","ravohary@gmail.com" ,"123","Magasinier",services.get(2)));
            usersList.add(new Users(8L,"Prisca Fehiarisoa","mytyrealy@gmail.com" ,"123","DAF",services.get(3)));
            userRepository.saveAll(usersList);

//            Entreprise informations
            EntrepriseInformation e= new EntrepriseInformation(1L,"MYTY","mytyrealy@Gmail.com","0347067949", "Lot III E 132 CT Fort Voyron");
            entrepriseInformationRepository.save(e);

            //fournisseur

            Fournisseur fournisseurs = new Fournisseur(1L,"prisca", "fortVoyron","0341234567","mee",3000,"mytyrealy@gmail.com");
            Fournisseur fournisseurs1 = new Fournisseur(2L,"ravo", "fortVoyron","0341234567","mee",3000,"jeddyranivo@gmail.com");
            Fournisseur fournisseurs2 = new Fournisseur(3L,"jeddy", "fortVoyron","0341234567","mee",3000,"faniryraely22@gmail.com");

            fournisseurRepository.saveAll(List.of(fournisseurs1,fournisseurs2,fournisseurs));

             List<Produit> produits= new ArrayList<>();
            produits.add(new Produit(1L,"gel desinfectant",0));
            produits.add(new Produit(2L,"alcool 90",0));
            produits.add(new Produit(3L,"masque ",0));
            produits.add(new Produit(4L,"spray",0));

            produitRepository.saveAll(produits);


            List<Produit_Fournisseur> produitFournisseurs= new ArrayList<>();
            produitFournisseurs.add(new Produit_Fournisseur(1L,fournisseurs,produits.get(0),5,30,0, Timestamp.from(Instant.now()),30,2000));
            produitFournisseurs.add(new Produit_Fournisseur(2L,fournisseurs,produits.get(1),5,30,0, Timestamp.from(Instant.now()),30,3000));
            produitFournisseurs.add(new Produit_Fournisseur(3L,fournisseurs,produits.get(2),5,30,0, Timestamp.from(Instant.now()),30,4000));
            produitFournisseurs.add(new Produit_Fournisseur(4L,fournisseurs,produits.get(3),5,30,0, Timestamp.from(Instant.now()),30,5000));

            produit_fournisseurRepository.saveAll(produitFournisseurs);
            List<Long> fournisseur= new ArrayList<>();
            fournisseur.add(1L);
            fournisseur.add(2L);
            fournisseur.add(3L);
            List<Long> produit=new ArrayList<>();
            produit.add(1L);
            produit.add(2L);
            produit.add(3L);

            Optional<Users> users= userRepository.getUsersByEmailAndAndPassword("mytyrealy@gmail.com","123456");
            System.out.println("=|=|="+users.isPresent());


            List<VStock> vs=vStockRepository.getResteStockProduitOrdered(1L,"ASC");
            vs.forEach(System.out::println);

            SortieStock sortieStock= new SortieStock(produits.get(0),400,Timestamp.from(Instant.now()));
            System.out.println("=========");
            sortieStock.decomposition(vStockRepository).forEach(System.out::println);
//            FactureFournisseur f1=new FactureFournisseur(5L,123,123, LocalDateTime.now(),"texte.txt",fournisseurRepository);

        };
    }
}
