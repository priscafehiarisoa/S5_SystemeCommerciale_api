package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Besoin_produit;
import s5_systemecommerciale_api.model.Produit;

import java.util.List;

public interface BesoinRepository extends JpaRepository<Besoin,Long> {

    @Query(value = "select b from Besoin b where b.etat =-1")
    List<Besoin>getAllDeletedBesoin();


    @Query(value = "select b from Besoin b where b.etat =0")
    List<Besoin>getAllBesoinEnCoursDeValidation();

    @Query(value = "select b from Besoin b where b.etat =10")
    List<Besoin>getAllBesoinValide();

    @Modifying
    @Transactional
    @Query(value = "update Besoin p set p.etat=-1 where p.id = :id")
    void deleteBesoin( Long id);

    @Modifying
    @Transactional
    @Query(value = "update Besoin p set p.etat=10 where p.id = :id")
    void validerBesoin( Long id);

}
