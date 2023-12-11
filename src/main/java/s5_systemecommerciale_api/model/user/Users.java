package s5_systemecommerciale_api.model.user;

import jakarta.persistence.*;
import lombok.Data;
import s5_systemecommerciale_api.model.elses.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String poste;
    @ManyToOne
    @JoinColumn(name="idService")
    private Service service;

    public void setEmail(String email) throws Exception {
        if(checkEmail(email)) {
            this.email = email;
        }
        else{
            throw new Exception("email invalide ");
        }
    }

    public boolean checkEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Users(String name, String email, String password, String poste, Service service) throws Exception {
        setName(name);
        setEmail(email);
        this.password = password;
        this.poste = poste;
        this.service = service;
    }

    public Users(Long id, String name, String email, String password, String poste, Service service) throws Exception {
        this.id = id;
        setName(name);
        setEmail(email);
        this.password = password;
        this.poste = poste;
        this.service = service;
    }

    public Users() {
    }
}
