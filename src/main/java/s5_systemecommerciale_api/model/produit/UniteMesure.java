package s5_systemecommerciale_api.model.produit;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UniteMesure {
    @Id
    private Long id;
    private String nomUnite;
}
