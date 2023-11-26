package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.bonDeCommande.BonsDeCommande;

import java.util.List;

public interface BonDeCommandeRepository extends JpaRepository<BonsDeCommande,Long> {

    BonsDeCommande findBonsDeCommandeByBesoin_Id(Long besoinId);

    List<BonsDeCommande> findFirstByBesoin_Id(Long besoinId);
    List<BonsDeCommande> findAllByBesoin_Id(Long besoin);


}
