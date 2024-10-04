package s5_systemecommerciale_api.model.BonsDeReception;

import jakarta.persistence.*;
import lombok.Data;
import s5_systemecommerciale_api.model.produit.Produit;
import s5_systemecommerciale_api.repository.BonsDeReceptionRepository;
import s5_systemecommerciale_api.repository.ProduitRepository;

import java.util.*;

@Entity
@Data
public class DetailsBonsReception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idBonReception")
    private BonsDeReception bonsDeReception;
    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;
    private double quantiteProduit;

    public DetailsBonsReception() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BonsDeReception getBonsDeReception() {
        return bonsDeReception;
    }

    public void setBonsDeReception(BonsDeReception bonsDeReception) {
        this.bonsDeReception = bonsDeReception;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public void setProduit(Long idProduit, ProduitRepository produitRepository) throws Exception {
        Optional<Produit> produit1= produitRepository.findById(idProduit);
        if(produit1.isPresent()){
            setProduit(produit1.get());
        }
        else{
            throw new Exception("produit inéxistant");
        }
    }

    public double getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(double quantiteProduit) throws Exception {
        if(quantiteProduit<0){
            throw new Exception("la quantité de produit est négative");
        }


        this.quantiteProduit = quantiteProduit;
    }
    public void setQuantiteProduit(Object quantiteProduit) throws Exception {
        String value= String.valueOf(quantiteProduit);
        if(Objects.equals(value, "") || value==null){
            throw new Exception("la quantité de produit est nulle");
        }
        double qtt= Double.parseDouble(value);
        setQuantiteProduit(qtt);
    }

    public DetailsBonsReception(BonsDeReception bonsDeReception, Produit produit, double quantiteProduit) throws Exception {
        setBonsDeReception(bonsDeReception);
        setProduit(produit);
        setQuantiteProduit(quantiteProduit);
    }
    public DetailsBonsReception(BonsDeReception bonsDeReception, Long idproduit, Object quantiteProduit, ProduitRepository produitRepository) throws Exception {
        setBonsDeReception(bonsDeReception);
        setProduit(idproduit,produitRepository);
        setQuantiteProduit(quantiteProduit);
    }

    public static List<DetailsBonsReception> createDetailBonReceptFromHashMap(List<HashMap<String,Object>> hashmap , BonsDeReception bondeReception, BonsDeReceptionRepository bonsDeReceptionRepository, ProduitRepository produitRepository) throws Exception {
        List<DetailsBonsReception> detailsBonsReceptions=new ArrayList<>();
        for (int i = 0; i < hashmap.size(); i++) {
            Long idProduit = Long.valueOf(String.valueOf(hashmap.get(i).get("produits")));
            detailsBonsReceptions.add(new DetailsBonsReception(bondeReception,idProduit,hashmap.get(i).get("quantite"),produitRepository));
        }
        return detailsBonsReceptions;
    }



}
