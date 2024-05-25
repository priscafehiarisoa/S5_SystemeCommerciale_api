package s5_systemecommerciale_api.model.Factures;

import jakarta.persistence.*;
import lombok.Data;
import s5_systemecommerciale_api.model.fournisseur.Fournisseur;
import s5_systemecommerciale_api.repository.FactureFournisseurRepository;
import s5_systemecommerciale_api.repository.FournisseurRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Entity
public class FactureFournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idFournisseur")
    private Fournisseur fournisseur;

    private double totalPrixTTC;
    private double totalPrixHT;
    private LocalDateTime dateFacture;
    private String nomDuFichierFActure;

    public FactureFournisseur() {

    }

    public void setTotalPrixHT(double totalPrixHT) throws Exception {
        if(totalPrixHT<0){
            throw new Exception("prix Hors taxe negative");
        }
        this.totalPrixHT = totalPrixHT;
    }

    public void setTotalPrixTTC(double totalPrixTTC) throws Exception {
        if(totalPrixTTC<0){
            throw new Exception("prix TTC negative");
        }
        this.totalPrixTTC = totalPrixTTC;
    }

    public FactureFournisseur(Long id, Fournisseur fournisseur, double totalPrixTTC, double totalPrixHT, LocalDateTime dateFacture, String nomDuFichierFActure) throws Exception {
        setId(id);
        setFournisseur(fournisseur);
        setTotalPrixTTC(totalPrixTTC);
        setTotalPrixHT(totalPrixHT);
        setDateFacture(dateFacture);
        setNomDuFichierFActure(nomDuFichierFActure);
    }

    public FactureFournisseur(Fournisseur fournisseur, double totalPrixTTC, double totalPrixHT, LocalDateTime dateFacture, String nomDuFichierFActure) throws Exception {
        setFournisseur(fournisseur);
        setTotalPrixTTC(totalPrixTTC);
        setTotalPrixHT(totalPrixHT);
        setDateFacture(dateFacture);
        setNomDuFichierFActure(nomDuFichierFActure);
    }

    public FactureFournisseur (Long idFournisseur, double totalPrixTTC, double totalPrixHT, LocalDateTime dateFacture, String nomDuFichierFActure , FournisseurRepository fournisseurRepository) throws Exception {
        Optional<Fournisseur> fournisseur=fournisseurRepository.findById(idFournisseur);
        if(fournisseur.isPresent()){
            setFournisseur(fournisseur.get());
        }
        else{
            throw new Exception("fournisseur inexistant ");
        }
        setTotalPrixTTC(totalPrixTTC);
        setTotalPrixHT(totalPrixHT);
        setDateFacture(dateFacture);
        setNomDuFichierFActure(nomDuFichierFActure);
    }

    public void saveFactureFournisseur(FactureFournisseurRepository factureFournisseurRepository){
        factureFournisseurRepository.save(this);
    }


}
