package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import s5_systemecommerciale_api.model.Produit;
import s5_systemecommerciale_api.model.Produit_Fournisseur;

import java.util.List;

public interface Produit_fournisseurRepository extends JpaRepository<Produit_Fournisseur,Long> {
    @Query(value = "select p from Produit_Fournisseur p where p.etat =0")
    List<Produit_Fournisseur> getAllValidateproduit();

    @Modifying
    @Transactional
    @Query(value = "update Produit_Fournisseur p set p.etat=-1 where p.id = :id")
    void deleteproduitFournisseur( Long id);
}
