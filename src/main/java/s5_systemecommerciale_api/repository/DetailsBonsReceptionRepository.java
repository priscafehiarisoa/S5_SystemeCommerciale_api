package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s5_systemecommerciale_api.model.BonsDeReception.BonsDeReception;
import s5_systemecommerciale_api.model.BonsDeReception.DetailsBonsReception;

import java.util.List;

@Repository
public interface DetailsBonsReceptionRepository extends JpaRepository<DetailsBonsReception, Long> {

    List<DetailsBonsReception> getDetailsBonsReceptionByBonsDeReception(BonsDeReception bonsDeReception);
}