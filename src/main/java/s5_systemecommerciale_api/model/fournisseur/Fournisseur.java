package s5_systemecommerciale_api.model.fournisseur;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Getter
@Setter
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="fournisseur_seq" )
    @SequenceGenerator(name = "fournisseur_seq" , sequenceName = "fournisseur_seq", allocationSize = 1)
    @Column(name="id_fournisseur")
    Long id;

    String nom_fournisseur;
    String adresse;
    String telephone;
    String nom_responsable;
    double prix_livraison;

    String email;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fournisseur", cascade = CascadeType.ALL)
//    List<Produit_Fournisseur> produitFournisseurs;

    public Fournisseur(Long id, String nom_fournisseur, String adresse, String telephone, String nom_responsable, double prix_livraison, String email) throws Exception {
        setId(id);
        setNom_fournisseur(nom_fournisseur);
        setAdresse(adresse);
        setTelephone(telephone);
        setNom_responsable(nom_responsable);
        setPrix_livraison(prix_livraison);
        setEmail(email);
    }

    public Fournisseur(String nom_fournisseur, String adresse, String telephone, String nom_responsable, double prix_livraison,String email) throws Exception {
        setNom_fournisseur(nom_fournisseur);
        setAdresse(adresse);
        setTelephone(telephone);
        setNom_responsable(nom_responsable);
        setPrix_livraison(prix_livraison);
        setEmail(email);
    }

    public Fournisseur() {
    }

    public void setEmail(String email) throws Exception {
        if(checkEmailValid(email)){
            this.email = email;
        }
        else{
            throw new Exception("email non valide");
        }

    }

    public static boolean checkEmailValid(String email) {
        // Expression régulière pour la validation d'une adresse e-mail
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

        // Créer un objet Pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Créer un objet Matcher avec l'adresse e-mail en argument
        Matcher matcher = pattern.matcher(email);

        // Vérifier si l'adresse e-mail correspond à l'expression régulière
        return matcher.matches();
    }


    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + id +
                ", nom_fournisseur='" + nom_fournisseur + '\'' +
                ", Adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", nom_responsable='" + nom_responsable + '\'' +
                ", prix_livraison=" + prix_livraison +
                '}';
    }

    public void setPrix_livraison(double prix_livraison) {
        this.prix_livraison = prix_livraison;
    }

    public void setPrix_livraison(String prix_livraison) throws Exception {
        if(prix_livraison ==null){
            throw new Exception("valeur ne peux pas etre nulle");
        }
        try {
            double prixdouble=Double.parseDouble((prix_livraison));
            if(prixdouble<0)
            {
                throw new Exception(("valeur ne peux pas etre negative"));
            }
            this.prix_livraison = prixdouble;


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
