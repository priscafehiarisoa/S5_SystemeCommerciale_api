package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.Factures.FactureFournisseur;

public interface FactureFournisseurRepository extends JpaRepository<FactureFournisseur,Long> {
}
