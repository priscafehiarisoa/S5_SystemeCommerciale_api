package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.besoins.Besoin_produit;

import java.util.List;

public interface Besoin_produitRepository extends JpaRepository<Besoin_produit,Long> {

    List<Besoin_produit> findAllByBesoin_Id(Long id_besoin);


}
