package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.Fournisseur;
import s5_systemecommerciale_api.repository.FournisseurRepository;

import java.util.List;

@Service
public class FournisseurService {
    @Autowired
    FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseur(){
        return fournisseurRepository.findAll();
    }

    public void addNewFournisseur(Fournisseur fournisseur){
        fournisseurRepository.save(fournisseur);
    }

    public void deleteFournisseur(long id) throws IllegalAccessException {
        if(!fournisseurRepository.existsById((long) id))
        {
            throw new IllegalAccessException("fournisseur n existe pas");
        }
        fournisseurRepository.deleteById((long) id);
    }

}
