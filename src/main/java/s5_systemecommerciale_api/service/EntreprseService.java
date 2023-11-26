package s5_systemecommerciale_api.service;

import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.entreprise.EntrepriseInformation;
import s5_systemecommerciale_api.repository.EntrepriseInformationRepository;

import java.util.Optional;

@Service
public class EntreprseService {
    private final EntrepriseInformationRepository entrepriseInformationRepository;

    public EntreprseService(EntrepriseInformationRepository entrepriseInformationRepository) {
        this.entrepriseInformationRepository = entrepriseInformationRepository;
    }

    public EntrepriseInformation getEntrepriseInformation () throws Exception {
       Optional<EntrepriseInformation> entrepriseInformation= entrepriseInformationRepository.findById(1L);
       if(entrepriseInformation.isPresent()) {
           return entrepriseInformation.get();
       }
       else{
           throw new Exception("aucune information sur l'entreprise");
       }

    }
}
