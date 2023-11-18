package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.Produit;
import s5_systemecommerciale_api.model.Service;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
