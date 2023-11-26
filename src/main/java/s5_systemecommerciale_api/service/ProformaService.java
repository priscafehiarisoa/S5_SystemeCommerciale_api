package s5_systemecommerciale_api.service;

import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.besoins.Besoin;
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


    public List<List<Article>> listeArticlesSelonBesoin(Besoin besoin){
        Besoinmodel besoinmodel=new Besoinmodel(besoin,besoin_produitRepository);
        List<Article> listeArticle= new ArrayList<>();
        System.out.println("besoin : "+besoinmodel.getListeBesoin().size());
        List<List<Produit_Fournisseur>> pf = produit_fournisseurService.getNmeilleurPrix(besoin,1);
        List<Produit_Fournisseur> produitFournisseurs=new ArrayList<>();
        for (int i = 0; i < pf.size(); i++) {
            produitFournisseurs.addAll(pf.get(i));
        }
        System.out.println("niiiiiii");
        pf.forEach(System.out::println);
        System.out.println("noooooo");
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

        System.out.println("niou");
        // Print the result
        sortedList.forEach(System.out::println);
        System.out.println("luio");

        return sortedList;
    }
}
