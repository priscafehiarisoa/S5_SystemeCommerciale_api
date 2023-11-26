package s5_systemecommerciale_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;

import javax.swing.*;

public interface EntrepriseInformationRepository extends JpaRepository<EntrepriseInformation,Long> {
}
