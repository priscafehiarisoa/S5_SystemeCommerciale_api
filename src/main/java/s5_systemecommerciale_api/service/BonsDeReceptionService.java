package s5_systemecommerciale_api.service;

import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.BonsDeReception.BonsDeReception;
import s5_systemecommerciale_api.model.BonsDeReception.DetailsBonsReception;
import s5_systemecommerciale_api.model.Factures.FactureFournisseur;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommande;
import s5_systemecommerciale_api.repository.BonsDeReceptionRepository;
import s5_systemecommerciale_api.repository.DetailBDR_Repository;
import s5_systemecommerciale_api.repository.FactureFournisseurRepository;

import java.util.List;

@Service
public class BonsDeReceptionService {
    private final FactureFournisseurRepository factureFournisseurRepository;
    private final BonsDeReceptionRepository bonsDeReceptionRepository;
    private final DetailBDR_Repository detailBDR_Repository;
    private final BonsDeCommandeService bonsDeCommandeService;

    public BonsDeReceptionService(FactureFournisseurRepository factureFournisseurRepository, BonsDeReceptionRepository bonsDeReceptionRepository1, DetailBDR_Repository detailBDR_Repository,BonsDeCommandeService bonsDeCommandeService) {
        this.factureFournisseurRepository = factureFournisseurRepository;
        this.bonsDeReceptionRepository = bonsDeReceptionRepository1;
        this.detailBDR_Repository = detailBDR_Repository;
        this.bonsDeCommandeService = bonsDeCommandeService;
    }

    public void saveBonsDeReception(BonsDeReception bonsDeReception, FactureFournisseur factureFournisseur, List<DetailsBonsReception> detailsBonsReceptionList){
        // enregistrer la facture
        factureFournisseur.saveFactureFournisseur(factureFournisseurRepository);
        // creer le bons de récéption
        bonsDeReception.setFactureFournisseur(factureFournisseur);
        bonsDeReceptionRepository.save(bonsDeReception);
        // ajouter le bons de récéption au detail de bdr
        for (int i = 0; i < detailsBonsReceptionList.size(); i++) {
            detailsBonsReceptionList.get(i).setBonsDeReception(bonsDeReception);
            detailBDR_Repository.save(detailsBonsReceptionList.get(i));
        }
    }
}
