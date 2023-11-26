package s5_systemecommerciale_api.model.entreprise;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class EntrepriseInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomEntreprise;
    private String email;
    private String phone;
    private String adresse;

    public EntrepriseInformation(Long id, String nomEntreprise, String email, String phone,String adresse) {
        setId(id);
        setNomEntreprise(nomEntreprise);
        setEmail(email);
        setPhone(phone);
        setAdresse(adresse);
    }

    public EntrepriseInformation(String nomEntreprise, String email, String phone,String adresse) {
        setNomEntreprise(nomEntreprise);
        setEmail(email);
        setPhone(phone);
        setAdresse(adresse);
    }

    public EntrepriseInformation() {
    }
}
