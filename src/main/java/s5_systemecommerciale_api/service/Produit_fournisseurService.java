package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.besoins.Besoin;
import s5_systemecommerciale_api.model.besoins.Besoin_produit;
import s5_systemecommerciale_api.model.besoins.Besoinmodel;
import s5_systemecommerciale_api.model.fournisseur.Fournisseur;
import s5_systemecommerciale_api.model.fournisseur.Produit_Fournisseur;
import s5_systemecommerciale_api.model.produit.Produit;
import s5_systemecommerciale_api.repository.Besoin_produitRepository;
import s5_systemecommerciale_api.repository.FournisseurRepository;
import s5_systemecommerciale_api.repository.ProduitRepository;
import s5_systemecommerciale_api.repository.Produit_fournisseurRepository;
import s5_systemecommerciale_api.service.files.ExelReadingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Produit_fournisseurService {

    @Autowired
    Produit_fournisseurRepository produitFournisseurRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private Besoin_produitRepository besoin_produitRepository;
    @Autowired
    private ExelReadingService exelReadingService;

    public void AddnewProduitFournisseur(Produit_Fournisseur produitFournisseur) throws Exception {
        // verifier si on possède déjà un produit du fournisseur dans la base de donnée
        Optional<Produit_Fournisseur> pf= produitFournisseurRepository.getValideProduitFournisseurByIdProduit(produitFournisseur.getProduit().getId(),produitFournisseur.getFournisseur().getId());
        Optional <Fournisseur> fournisseur= fournisseurRepository.findById(produitFournisseur.getFournisseur().getId());
        Optional <Produit> produit= produitRepository.findById(produitFournisseur.getProduit().getId());
        // update le pf s'il existe
        if(pf.isPresent()){
            Produit_Fournisseur pf2= pf.get();
            produitFournisseurRepository.makeProduitFournisseurUnavailable(pf2.getId());
        }
        if(fournisseur.isEmpty()){
            throw new Exception("fournisseur introuvable impossible de l'inserer dans une base ");
        }
        if(produit.isEmpty()){
            throw new Exception("produit introuvable impossible de l'inserer dans une base");
        }
        Produit_Fournisseur prod= new Produit_Fournisseur(fournisseur.get(), produit.get(), produitFournisseur.getTaxe(), produitFournisseur.getDelaiDeLivraison(), produitFournisseur.getEtat(), produitFournisseur.getDate(),produitFournisseur.getPrixHorsTaxe(), produitFournisseur.getQuantiteStock());
        produitFournisseurRepository.save(prod);
    }

    // obtenir les 3 meilleurs prix a partir des prix qu'on a dans la base
    public List<List<Produit_Fournisseur>> get3meilleurPrix(Besoin besoin){
        Besoinmodel besoinmodel=new Besoinmodel(besoin,besoin_produitRepository);
        List<List<Produit_Fournisseur>> response = new ArrayList<>();
        for (int i = 0; i < besoinmodel.getListeBesoin().size(); i++) {
            Optional<Produit> produit= produitRepository.findById(besoinmodel.getListeBesoin().get(i).getProduit().getId());
            if(produit.isPresent()){
                List<Produit_Fournisseur> liste= produitFournisseurRepository.getMini_proforma(produit.get());
                response.add(liste);
            }
        }
        return response;
    }

    // obtenir les n meilleurs prix a partir des prix qu'on a dans la base
    public List<List<Produit_Fournisseur>> getNmeilleurPrix(Besoin besoin,int limit){
        Besoinmodel besoinmodel=new Besoinmodel(besoin,besoin_produitRepository);

        List<List<Produit_Fournisseur>> response = new ArrayList<>();
        for (int i = 0; i < besoinmodel.getListeBesoin().size(); i++) {
            Optional<Produit> produit= produitRepository.findById(besoinmodel.getListeBesoin().get(i).getProduit().getId());
            if(produit.isPresent()){
                Pageable pages = PageRequest.of(0, limit);
                List<Produit_Fournisseur> liste= produitFournisseurRepository.get_n_Mini_proforma(produit.get(),pages);
                List<Produit_Fournisseur> pff= decomposer_niv1(liste,besoinmodel.getListeBesoin().get(i));
                System.out.println("==-==-==-==");
                pff.forEach(System.out::println);
                System.out.println("==-==-==-==");
                response.add(liste);
            }
        }
        return response;
    }

    public List<Produit_Fournisseur> decomposer(List<List<Produit_Fournisseur>> produits){
        /*** ny quantite ao @ le stock io lasa le quantite miditra any @ le articles */
        List<Produit_Fournisseur> produitFournisseurs=new ArrayList<>();
        for (int i = 0; i < produits.size(); i++) {

        }
        return produitFournisseurs;
    }

    public List<Produit_Fournisseur> decomposer_niv1(List<Produit_Fournisseur> produitFournisseurs, Besoin_produit besoinProduit){
        List<Produit_Fournisseur> produitFournisseurs1= new ArrayList<>();
        double quantiteInitiale=besoinProduit.getQuantite();
        for (int i = 0; i < produitFournisseurs.size() && quantiteInitiale>0; i++) {

            if(produitFournisseurs.get(i).getQuantiteStock()>0 && produitFournisseurs.get(i).getQuantiteStock()>quantiteInitiale){
                Produit_Fournisseur pf= produitFournisseurs.get(i);
                pf.setQuantiteStock(quantiteInitiale);
                produitFournisseurs1.add(pf);
                break;

            } else if (produitFournisseurs.get(i).getQuantiteStock()>0 && produitFournisseurs.get(i).getQuantiteStock()<quantiteInitiale) {
                Produit_Fournisseur pf= produitFournisseurs.get(i);
                produitFournisseurs1.add(pf);
                quantiteInitiale=quantiteInitiale-pf.getQuantiteStock();

            }
        }
        return produitFournisseurs1;
    }

    public void saveFromExel(String path,Long idFournisseur) throws Exception {
        List<Produit_Fournisseur>pf=exelReadingService.readBooksFromExcelFile(path,idFournisseur);
        pf=new Produit_Fournisseur().trier(pf);
        for (int i = 0; i < pf.size(); i++) {
            AddnewProduitFournisseur(pf.get(i));
        }
    }

}
