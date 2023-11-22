package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.BonsDeCommandeArticle;

import java.util.List;

public interface BonDeCommandeArticleRepository extends JpaRepository<BonsDeCommandeArticle,Long> {
    List<BonsDeCommandeArticle> findAllByIdBonDeCommande(Long idBonDeCommande);
}
