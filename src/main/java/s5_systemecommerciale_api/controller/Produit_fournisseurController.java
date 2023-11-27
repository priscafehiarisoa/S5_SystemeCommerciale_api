package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import s5_systemecommerciale_api.model.besoins.Besoin;
import s5_systemecommerciale_api.model.fournisseur.Produit_Fournisseur;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.repository.Produit_fournisseurRepository;
import s5_systemecommerciale_api.service.Produit_fournisseurService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin()
@RestController
@RequestMapping("/produitFournisseur")
public class Produit_fournisseurController {

    @Autowired
    Produit_fournisseurService produitFournisseurService;
    @Autowired
    Produit_fournisseurRepository produitFournisseurRepository;
    @Autowired
    private BesoinRepository besoinRepository;
    @Autowired
    private Produit_fournisseurService produit_fournisseurService;

    @GetMapping
    public List<Produit_Fournisseur> getAllValidateProduitFournisseur(){
        return produitFournisseurRepository.getAllValidateproduit();
    }

    @GetMapping("/Proforma_3_fournisseur/{idBesoin}")
    public List<List<Produit_Fournisseur>> getProforma_3_fournisseur(@PathVariable Long idBesoin){
        Optional<Besoin> besoin= besoinRepository.findById(idBesoin);
        List<List<Produit_Fournisseur>> lp=new ArrayList<>();
        if(besoin.isPresent()){
           lp = produitFournisseurService.get3meilleurPrix(besoin.get());

        }
        return lp;
    }

    @PostMapping
    public void insert(@RequestBody Produit_Fournisseur produitFournisseur) throws Exception {
        produitFournisseurService.AddnewProduitFournisseur(produitFournisseur);
    }

    @PutMapping("/effacer/{id}")
    public  void  deleteProduitFournisseur(@PathVariable Long id){
        produitFournisseurRepository.deleteproduitFournisseur(id);
    }

    @PostMapping("/savePFfromExel")
    public ResponseEntity<?> getL(@RequestBody Map<String,String> path) throws Exception {

        String excelFilePath = path.get("path");
        Long idFournisseur = Long.valueOf(path.get("idFournisseur"));
        produitFournisseurService.saveFromExel(excelFilePath,idFournisseur);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(
            @RequestPart("file") MultipartFile file,
            @RequestParam("fournisseur") Long idfournisseur)
             {
        try {
            System.out.println("idFournisseur "+idfournisseur);
            String uploadDirectory = System.getProperty("user.dir") + "/uploads/";
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDirectory, fileName);
            String filePathString=uploadDirectory+fileName;

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            //call the function that treats the exels
            produitFournisseurService.saveFromExel(filePathString,idfournisseur);

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }
    @GetMapping("/test")
    public void test(){
        Besoin b= besoinRepository.findById(19L).get();
        List<List<Produit_Fournisseur>> pf=produit_fournisseurService.getNmeilleurPrix(b,3);

    }
}
