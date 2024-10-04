package s5_systemecommerciale_api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import s5_systemecommerciale_api.model.BonsDeReception.BonsDeReception;
import s5_systemecommerciale_api.model.BonsDeReception.DetailsBonsReception;
import s5_systemecommerciale_api.repository.DetailsBonsReceptionRepository;
import s5_systemecommerciale_api.model.Factures.FactureFournisseur;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommande;
import s5_systemecommerciale_api.model.user.Users;
import s5_systemecommerciale_api.repository.*;
import s5_systemecommerciale_api.repository.user.UserRepository;
import s5_systemecommerciale_api.service.BonsDeCommandeService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController("/bonsReception")
public class BonDeReceptionController {

    private final BonsDeCommandeService bonsDeCommandeService;
    private final FournisseurRepository fournisseurRepository;
    private final BonDeCommandeRepository bonDeCommandeRepository;
    private final UserRepository userRepository;
    private final BonsDeReceptionRepository bonsDeReceptionRepository;
    private final ProduitRepository produitRepository;
    private final FactureFournisseurRepository factureFournisseurRepository;
    private final DetailsBonsReceptionRepository detailsBonsReceptionRepository;

    public BonDeReceptionController(BonsDeCommandeService bonsDeCommandeService, FournisseurRepository fournisseurRepository, BonDeCommandeRepository bonDeCommandeRepository, UserRepository userRepository,
                                    BonsDeReceptionRepository bonsDeReceptionRepository,
                                    ProduitRepository produitRepository,
                                    FactureFournisseurRepository factureFournisseurRepository,
                                    DetailsBonsReceptionRepository detailsBonsReceptionRepository) {
        this.bonsDeCommandeService = bonsDeCommandeService;
        this.fournisseurRepository = fournisseurRepository;
        this.bonDeCommandeRepository = bonDeCommandeRepository;
        this.userRepository = userRepository;
        this.bonsDeReceptionRepository = bonsDeReceptionRepository;
        this.produitRepository = produitRepository;
        this.factureFournisseurRepository = factureFournisseurRepository;
        this.detailsBonsReceptionRepository = detailsBonsReceptionRepository;
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

    @PostMapping("/saveFileFActure")
    public HashMap<String , Object > saveFileFacture(@RequestPart MultipartFile file) throws IOException {
//        upload fa/cture file
        HashMap<String,Object> hashMap= new HashMap<>();
            String uploadDirectory = System.getProperty("user.dir") + "/upload_file_factures/";
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDirectory, fileName);
            String filePathString=uploadDirectory+fileName;
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//end upload
        hashMap.put("saved","file saved");
        return hashMap;
    }


    @PostMapping("/saveBonsDeReception2")
    public HashMap<String , Object> saveBonsDeReception2(
            @RequestBody HashMap<String,Object> hashmap
    ){
        System.out.println("ato");
        // Facture Fournisseu
        HashMap<String , Object> returnHashMap= new HashMap<>();
        try{
            String fileFactureName= String.valueOf(hashmap.get("filename"));

            //to be saved later
            FactureFournisseur factureFournisseur =new FactureFournisseur();
            factureFournisseur.setNomDuFichierFActure(fileFactureName);
            BonsDeReception bonsDeReception=new BonsDeReception();
            List<DetailsBonsReception> detailsBonsReceptionList=new ArrayList<>();
            //end
            List<HashMap<String,Object>> detail =new ArrayList<>();
            LocalDateTime dateFacture= parseToLocaldateTime(String.valueOf(hashmap.get("dateFacture"))) ;

//            test if has a bon de commande
            Long idBOndecommande= Long.valueOf(String.valueOf(hashmap.get("idBonsDeCommande")) ) ;
            Optional<BonsDeCommande> bonsDeCommandeOptional=bonDeCommandeRepository.findById(idBOndecommande);
            if(bonsDeCommandeOptional.isPresent()) {

                System.out.println(Long.valueOf(String.valueOf(hashmap.get("idFournisseur"))) + "  " + Double.parseDouble(String.valueOf(hashmap.get("prixTTC"))) + "  " + Double.parseDouble(String.valueOf(hashmap.get("prixHt"))) + "  " + dateFacture + "  " + (String) hashmap.get("filename"));
                //creation de facture
                factureFournisseur= new FactureFournisseur(Long.valueOf(String.valueOf(hashmap.get("idFournisseur"))) ,Double.parseDouble(String.valueOf(hashmap.get("prixTTC"))) , Double.parseDouble(String.valueOf(hashmap.get("prixHt"))) ,dateFacture , (String) hashmap.get("fileFacture"), fournisseurRepository);


                LocalDateTime dateDeReception= parseToLocaldateTime(String.valueOf(hashmap.get("dateReception")));
                String nomLivreur=(String) hashmap.get("nomDuLivreur");

                // Bons de reception
                //test existence de magasinier
                Optional<Users> magasinier= userRepository.findById(Long.valueOf(String.valueOf(hashmap.get("idMagasinier")) ));
                if(magasinier.isPresent()) {
                    bonsDeReception = new BonsDeReception(factureFournisseur, bonsDeCommandeOptional.get(), magasinier.get(), dateDeReception, nomLivreur);
                    // detailsBonsDeReception
                    detail=  (List<HashMap<String, Object>>) hashmap.get("details");
                    detailsBonsReceptionList= DetailsBonsReception.createDetailBonReceptFromHashMap(detail,bonsDeReception,bonsDeReceptionRepository,produitRepository);
                }
                else {
                    System.out.println("tsisy magasinier");
                }
                System.out.println(hashmap.toString());
                factureFournisseurRepository.save(factureFournisseur);
                bonsDeReceptionRepository.save(bonsDeReception);
                detailsBonsReceptionRepository.saveAll(detailsBonsReceptionList);

            }
            else{
                returnHashMap.put("error","bon de commande inexistant");
                System.out.println("tsisy bon de commande");
            }
            returnHashMap.put("factureFournisseur",factureFournisseur);
            returnHashMap.put("bonDeREception",bonsDeReception);
            returnHashMap.put("details ",detailsBonsReceptionList);
            System.out.println(factureFournisseur);

            //save all


        } catch (Exception exception){
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

    @GetMapping("/listBonDeReception")
    public List<HashMap<String,Object>> getBonDeReception(){
        List<BonsDeReception> bdc=bonsDeReceptionRepository.findAll();
       List<HashMap<String,Object>> ret=new ArrayList<>();
        for (int i = 0; i < bdc.size(); i++) {
            HashMap<String,Object> tobereturned=new HashMap<>();
            List<DetailsBonsReception> detailsBonsReceptionList= detailsBonsReceptionRepository.getDetailsBonsReceptionByBonsDeReception(bdc.get(i));
            tobereturned.put("bonDeReception",bdc.get(i));
            tobereturned.put("details",detailsBonsReceptionList);
            ret.add(tobereturned);
        }
        return ret;
    }

    @GetMapping("/listeFactures")
    public List<FactureFournisseur> getListeFacture (){
        return factureFournisseurRepository.findAll();
    }

    @PutMapping("/validateFacture1/{id}")
    public void validate1Facture(@PathVariable Long id){
        Optional<FactureFournisseur> fact=factureFournisseurRepository.findById(id);
        if(fact.isPresent()){
            factureFournisseurRepository.validerfacture1(id);
        }
    }
    @PutMapping("/validateFacture2/{id}")
    public void validate2Facture(@PathVariable Long id){
        Optional<FactureFournisseur> fact=factureFournisseurRepository.findById(id);
        if(fact.isPresent()){
            factureFournisseurRepository.validerfacture2(id);
        }
    }
}
