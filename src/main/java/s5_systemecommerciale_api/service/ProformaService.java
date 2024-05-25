package s5_systemecommerciale_api.service;

import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.besoins.Besoin;
import s5_systemecommerciale_api.model.besoins.Besoin_produit;
import s5_systemecommerciale_api.model.besoins.Besoinmodel;
import s5_systemecommerciale_api.model.fournisseur.Produit_Fournisseur;
import s5_systemecommerciale_api.model.produit.Article;
import s5_systemecommerciale_api.repository.Besoin_produitRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProformaService {
    private final Produit_fournisseurService produit_fournisseurService;
    private final Besoin_produitRepository besoin_produitRepository;

    public ProformaService(Produit_fournisseurService produit_fournisseurService,
                           Besoin_produitRepository besoin_produitRepository) {
        this.produit_fournisseurService = produit_fournisseurService;
        this.besoin_produitRepository = besoin_produitRepository;
    }

//    public Proforma getProforma(Besoin besoin ){
//        Proforma proforma= new Proforma();
//        proforma.setBesoinProduits(besoin.getBesoinProduits());
//        System.out.println("tyyy +++ ?? "+ besoin.getBesoinProduits().toString());
//        List<List<Produit_Fournisseur>> pf = produit_fournisseurService.getNmeilleurPrix(besoin,1);
//        List<Produit_Fournisseur> produitFournisseurs=new ArrayList<>();
//        for (int i = 0; i < pf.size(); i++) {
//            produitFournisseurs.addAll(pf.get(i));
//        }
//        proforma.setProduit_fournisseurList(produitFournisseurs);
//        proforma.setTotalPrixHt();
//        return proforma;


    public List<List<Article>> listeArticlesSelonBesoin(Besoin besoin) throws Exception {
        Besoinmodel besoinmodel=new Besoinmodel(besoin,besoin_produitRepository);
        List<Article> listeArticle= new ArrayList<>();
        List<List<Produit_Fournisseur>> pf = produit_fournisseurService.getNmeilleurPrix(besoin,1);
        List<Produit_Fournisseur> produitFournisseurs=new ArrayList<>();
        for (int i = 0; i < pf.size(); i++) {
            produitFournisseurs.addAll(pf.get(i));
        }

        // eto no tokony hicheck an'le izy ....
        checkProforma(besoinmodel.getListeBesoin(),produitFournisseurs);

        for (int i = 0; i < produitFournisseurs.size(); i++) {
            for (int j = 0; j < besoinmodel.getListeBesoin().size(); j++) {
                if(besoinmodel.getListeBesoin().get(j).getProduit().getId() == produitFournisseurs.get(i).getProduit().getId()){
                    listeArticle.add(new Article(besoinmodel.getListeBesoin().get(j),produitFournisseurs.get(i)));
                }
            }
        }
        Map<Long, List<Article>> groupedByFournisseurId = listeArticle.stream()
                .sorted(Comparator.comparing(article -> article.getFournisseur().getId()))
                .collect(Collectors.groupingBy(article -> article.getFournisseur().getId()));

        // Converting the map values to a List<List<Article>>
        List<List<Article>> sortedList = groupedByFournisseurId.values().stream()
                .collect(Collectors.toList());


        return sortedList;
    }

    public void checkProforma(List<Besoin_produit> besoinProduits, List<Produit_Fournisseur> produitFournisseurs) throws Exception {
        for (int i = 0; i < besoinProduits.size(); i++) {
            int check =0;
            for (int j = 0; j < produitFournisseurs.size(); j++) {
                if(besoinProduits.get(i).getProduit().getId()==produitFournisseurs.get(j).getProduit().getId()){
                    check++;
                }
            }
            if(check==0){
                throw new Exception("le produit "+besoinProduits.get(i).getProduit().getNomProduit() + " n'a pas de proforma valide");
            }
        }
    }



}
