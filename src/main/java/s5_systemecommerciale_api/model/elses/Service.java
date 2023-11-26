package s5_systemecommerciale_api.model.elses;

import jakarta.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="service_seq" )
    @SequenceGenerator(name = "service_seq" , sequenceName = "service_seq", allocationSize = 1)
    @Column(name="id_service")
    Long id;
    String nomService;

    public Service() {
    }

    public Service(String nomService) {
        this.nomService = nomService;
    }

    public Service(Long id, String nomService) {
        this.id = id;
        this.nomService = nomService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", nomService='" + nomService + '\'' +
                '}';
    }
}
