package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import s5_systemecommerciale_api.model.Fournisseur;
import s5_systemecommerciale_api.model.Produit;
import s5_systemecommerciale_api.model.Produit_Fournisseur;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    @Query(value = "select p from Produit p where p.etat =0")
    List<Produit> getAllproduit();

    @Query(value = "select p from Produit p where p.etat =10")
    List<Produit> getAllDeletedproduit();

    @Modifying
    @Transactional
    @Query(value = "update Produit p set p.etat=10 where p.id = :id")
    void deleteproduit( Long id);





}
