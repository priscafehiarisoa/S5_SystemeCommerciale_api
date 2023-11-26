package s5_systemecommerciale_api.model.mail;

import lombok.Getter;
import lombok.Setter;
import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;

import java.sql.Date;

@Getter
@Setter
public class MailStructure {
    private String sender;
    private String username;
    private String subject;
    private String body;
    private Date dateLimite;

    public MailStructure getMailStructureForDemandeProforma(Date dateLimite, EntrepriseInformation entrepriseInformation){
        setSender("mytyrealy@gmail.com");
        setUsername("MYTY");
        setSubject("Demande de Devis Proforma");
        setDateLimite(dateLimite);
        setBody("Madame, Monsieur,\n" +
                "\n" +
                "J'espère que ce message vous trouve bien. Je me permets de vous contacter au sujet de "+entrepriseInformation.getNomEntreprise()+" et de solliciter de votre part un devis proforma.\n" +
                "\n" +
                "\n" +
                "Pouvez-vous, s'il vous plaît, nous fournir un devis proforma comprenant les éléments suivants :\n" +
                "\n" +
                "Description détaillée des produits/services : spécifié dans le fichier pdf en attachement \n" +
                "Quantité que vous avez en stock\n" +
                "Prix unitaire de vos produits\n" +
                "Conditions de paiement : \n" +
                "Validité du devis : [Indiquez la période de validité du devis proforma.]\n" +
                "Nous sommes conscients de la qualité de vos services et avons hâte de collaborer avec vous. Si vous avez des informations supplémentaires ou des recommandations à partager, n'hésitez pas à les inclure dans votre réponse.\n" +
                "\n" +
                "Nous vous prions de bien vouloir nous faire parvenir ce devis proforma d'ici le "+dateLimite+". Nous vous remercions par avance pour votre réactivité et restons à votre disposition pour toute information complémentaire. \n \n" +
                "Cordialement,\n \n" +
                entrepriseInformation.getNomEntreprise()+"\n" +
                entrepriseInformation.getEmail()+"\n"+
                entrepriseInformation.getPhone()+"\n");
        return this;
    }

}
