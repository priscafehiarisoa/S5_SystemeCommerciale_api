package s5_systemecommerciale_api.model.vals;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import s5_systemecommerciale_api.model.vals.ArticleDTO;
public class MyPayLoadDTO implements Serializable {

    private String idlivreur;
    private String iddestinataire;
    private String contact_destinataire;
    private List<ArticleDTO> articles;

    public MyPayLoadDTO() {
    }


    @Override
    public String toString() {
        return "MyPayloadDTO{" +
                "idlivreur='" + idlivreur + '\'' +
                ", iddestinataire='" + iddestinataire + '\'' +
                ", contact_destinataire='" + contact_destinataire + '\'' +
//                ", articles=" + articles.get(0) +
                '}';
    }

    public String getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(String idlivreur) {
        this.idlivreur = idlivreur;
    }

    public String getIddestinataire() {
        return iddestinataire;
    }

    public void setIddestinataire(String iddestinataire) {
        this.iddestinataire = iddestinataire;
    }

    public String getContact_destinataire() {
        return contact_destinataire;
    }

    public void setContact_destinataire(String contact_destinataire) {
        this.contact_destinataire = contact_destinataire;
    }



    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }
}
