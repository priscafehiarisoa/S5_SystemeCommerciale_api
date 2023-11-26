package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.elses.Service;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
