package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.Besoin_produit;
import s5_systemecommerciale_api.model.Produit;

public interface Besoin_produitRepository extends JpaRepository<Besoin_produit,Long> {
}
