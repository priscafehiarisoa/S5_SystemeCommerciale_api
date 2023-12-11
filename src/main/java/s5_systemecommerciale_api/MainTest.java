package s5_systemecommerciale_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s5_systemecommerciale_api.model.Factures.FactureFournisseur;
import s5_systemecommerciale_api.model.elses.Service;
import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;
import s5_systemecommerciale_api.model.user.Users;
import s5_systemecommerciale_api.repository.*;
import s5_systemecommerciale_api.repository.user.UserRepository;
import s5_systemecommerciale_api.service.EmailSenderService;
import s5_systemecommerciale_api.service.Produit_fournisseurService;
import s5_systemecommerciale_api.service.ProformaService;

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
                                        EntrepriseInformationRepository entrepriseInformationRepository

    ){
        return args -> {
            // create services
            Service service=new Service(-1L,"DAF");
            Service service2=new Service(3L,"Achat");
            Service service3=new Service(1L,"IT");
            Service service4=new Service(2L,"Finances");
            serviceRepository.saveAll(List.of(service,service2,service3,service4));

            // create users
            List<Users> usersList=new ArrayList<>();
            usersList.add(new Users(2L,"Rakotoniary Nirina","nirina@gmail.com" ,"123","responsable",service3));
            usersList.add(new Users(3L,"Manohy Arivelo","manohy@gmail.com" ,"123","responsable",service3));
            usersList.add(new Users(4L,"Aina malala","Aina@gmail.com" ,"123","responsable",service4));
            usersList.add(new Users(5L,"Lahatra Tiana","lahatra@gmail.com" ,"123","employe",service4));
            usersList.add(new Users(6L,"Tafita Herry","tafita@gmail.com" ,"123","responsable",service4));
            usersList.add(new Users(7L,"Ravo Hary Rakotondrabary","ravohary@gmail.com" ,"123","Magasinier",service2));
            usersList.add(new Users(8L,"Prisca Fehiarisoa","mytyrealy@gmail.com" ,"123","DAF",service));
            userRepository.saveAll(usersList);

            //Entreprise informations
//            EntrepriseInformation e= new EntrepriseInformation(1L,"MYTY","mytyrealy@Gmail.com","0347067949", "Lot III E 132 CT Fort Voyron");
//            entrepriseInformationRepository.save(e);

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

            FactureFournisseur f1=new FactureFournisseur(5L,123,123, LocalDateTime.now(),"texte.txt",fournisseurRepository);

        };
    }
}
