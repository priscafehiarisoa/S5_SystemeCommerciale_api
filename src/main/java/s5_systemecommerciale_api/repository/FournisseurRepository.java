package s5_systemecommerciale_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s5_systemecommerciale_api.model.Fournisseur;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Long>{

}
