package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.elses.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service,Long> {


}
