package s5_systemecommerciale_api.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import s5_systemecommerciale_api.model.Produit;
import s5_systemecommerciale_api.model.Produit_Fournisseur;

import java.util.List;
import java.util.Optional;

public interface Produit_fournisseurRepository extends JpaRepository<Produit_Fournisseur,Long> {

    @Query(value = "select p from Produit_Fournisseur p where p.etat =0")
    List<Produit_Fournisseur> getAllValidateproduit();

    @Query(value = "select * from Produit_Fournisseur where etat =0 and id_produit=:id and id_fournisseur=:idFournisseur",nativeQuery = true)
    Optional<Produit_Fournisseur> getValideProduitFournisseurByIdProduit(@Param("id") Long idProduit,@Param("idFournisseur") Long idFournisseur);

    @Modifying
    @Transactional
    @Query(value = "update Produit_Fournisseur p set p.etat=-1 where p.id = :id")
    void deleteproduitFournisseur( Long id);

    @Modifying
    @Transactional
    @Query(value = "update Produit_Fournisseur p set p.etat=-2 where p.id = :id")
    void makeProduitFournisseurUnavailable( Long id);

    // obtenir les trois derniers proformat valide
    @Query(value = "select s from Produit_Fournisseur as s where s.produit=:idProduit and s.etat=0 order by s.prixHorsTaxe asc limit 3")
    List<Produit_Fournisseur> getMini_proforma (@Param ("idProduit") Produit produit);
    // obtenir les trois derniers proformat valide
    @Query(value = "select s from Produit_Fournisseur as s where s.produit=:idProduit and s.etat=0 order by s.prixHorsTaxe asc ")
    List<Produit_Fournisseur> get_n_Mini_proforma (@Param ("idProduit") Produit produit, Pageable pageable);
}
