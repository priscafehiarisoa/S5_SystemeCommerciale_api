package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.BonsDeReception.DetailsBonsReception;

public interface DetailBDR_Repository extends JpaRepository<DetailsBonsReception,Long> {
}
