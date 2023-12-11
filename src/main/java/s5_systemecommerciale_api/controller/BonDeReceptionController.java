package s5_systemecommerciale_api.controller;

import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.BonsDeReception.BonsDeReception;
import s5_systemecommerciale_api.model.Factures.FactureFournisseur;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommande;
import s5_systemecommerciale_api.model.user.Users;
import s5_systemecommerciale_api.repository.BonDeCommandeRepository;
import s5_systemecommerciale_api.repository.FournisseurRepository;
import s5_systemecommerciale_api.repository.user.UserRepository;
import s5_systemecommerciale_api.service.BonsDeCommandeService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController("/bonsReception")
public class BonDeReceptionController {

    private final BonsDeCommandeService bonsDeCommandeService;
    private final FournisseurRepository fournisseurRepository;
    private final BonDeCommandeRepository bonDeCommandeRepository;
    private final UserRepository userRepository;

    public BonDeReceptionController(BonsDeCommandeService bonsDeCommandeService, FournisseurRepository fournisseurRepository, BonDeCommandeRepository bonDeCommandeRepository, UserRepository userRepository) {
        this.bonsDeCommandeService = bonsDeCommandeService;
        this.fournisseurRepository = fournisseurRepository;
        this.bonDeCommandeRepository = bonDeCommandeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/checkBDC/{id}")
    public HashMap<String, String> checkBondDeCommsnde(@PathVariable Long id){
        boolean test=bonsDeCommandeService.checkExistenceBonDeCommande(id);
        HashMap<String, String> result=new HashMap<>();
        result.put("checked",String.valueOf(test));
        return result;
    }

    // fonction pour enregistrer

    /** hashmap
     * FACTURE
     * * fileFacture
     * * prixHt
     * * prixTTC
     * * idFournisseur
     * * dateFacture
     *
     * BONS DE RECEPTION
     * * idBonsDeCommande
     * * idMagasinier
     * * dateReception // raha null
     * * nomDuLivreur
     *
     * DETAILS BONS DE RECEPTION
     * * [produits,quantite]
     * */
    @PostMapping("/saveBonsDeReception")
    public HashMap<String , Object> saveBonsDeReception(@RequestBody HashMap<String,Object> hashmap){
        // Facture Fournisseu
        HashMap<String , Object> returnHashMap= new HashMap<>();
        try{

//            test if has a bon de commande
            Long idBOndecommande= Long.valueOf(String.valueOf(hashmap.get("idBonsDeCommande")) ) ;
            FactureFournisseur factureFournisseur =new FactureFournisseur();
            BonsDeReception bonsDeReception=new BonsDeReception();
            List<HashMap<String,Object>> detail =new ArrayList<>();
            LocalDateTime dateFacture= parseToLocaldateTime(String.valueOf(hashmap.get("dateFacture"))) ;
            Optional<BonsDeCommande> bonsDeCommandeOptional=bonDeCommandeRepository.findById(idBOndecommande);
            if(bonsDeCommandeOptional.isPresent())
            {

                factureFournisseur= new FactureFournisseur(Long.valueOf(String.valueOf(hashmap.get("idFournisseur"))) ,Double.parseDouble(String.valueOf(hashmap.get("prixTTC"))) , Double.parseDouble(String.valueOf(hashmap.get("prixHt"))) ,dateFacture , (String) hashmap.get("fileFacture"), fournisseurRepository);
                System.out.println(factureFournisseur.toString());
                Optional<Users> magasinier= userRepository.findById(Long.valueOf(String.valueOf(hashmap.get("idMagasinier")) ));
                LocalDateTime dateDeReception= parseToLocaldateTime(String.valueOf(hashmap.get("dateReception")));
                String nomLivreur=(String) hashmap.get("nomDuLivreur");

                // Bons de reception
                if(magasinier.isPresent()) {
                    bonsDeReception = new BonsDeReception(factureFournisseur, bonsDeCommandeOptional.get(), magasinier.get(), dateDeReception, nomLivreur);
                    // detailsBonsDeReception
                   detail=  (List<HashMap<String, Object>>) hashmap.get("details");
                    System.out.println("=================");
                    detail.forEach(System.out::println);
                }
                else {
                    System.out.println("tsisy magasinier");
                }
                System.out.println(hashmap.toString());
            }
            else{
                returnHashMap.put("error","bon de commande inexistant");
                System.out.println("tsisy bon de commande");
            }
            returnHashMap.put("factureFournisseur",factureFournisseur);
            returnHashMap.put("bonDeREception",bonsDeReception);
            returnHashMap.put("details ",detail);

        } catch (Exception exception){
            exception.printStackTrace();
            String error=exception.getMessage() +" | "+exception.getCause();
            returnHashMap.put("error",error);
        }


        return returnHashMap ;
    }

    public static LocalDateTime parseToLocaldateTime(String date){
        String dateString = "2023-12-07 12:30:45";

        // Define a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Parse the string into a LocalDateTime object
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime;

    }

    public static void main(String[] args) {
        System.out.println(parseToLocaldateTime(""));;
    }

}
