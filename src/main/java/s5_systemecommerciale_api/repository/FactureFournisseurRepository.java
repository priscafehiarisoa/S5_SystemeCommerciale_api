package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import s5_systemecommerciale_api.model.Factures.FactureFournisseur;

public interface FactureFournisseurRepository extends JpaRepository<FactureFournisseur,Long> {

    @Modifying
    @Transactional
    @Query(value = "update FactureFournisseur p set p.etat=10 where p.id = :id")
    void validerfacture1(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update FactureFournisseur p set p.etat=20 where p.id = :id")
    void validerfacture2(@Param("id") Long id);
}
