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
        Fournisseur  f2 = new Fournisseur(fournisseur.getNom_fournisseur(), fournisseur.getAdresse(), fournisseur.getTelephone(),fournisseur.getNom_responsable(),fournisseur.getPrix_livraison() );
        fournisseurRepository.save(fournisseur);
    }

    public void deleteFournisseur(long id) throws IllegalAccessException {
        if(!fournisseurRepository.existsById(id))
        {
            throw new IllegalAccessException("fournisseur n existe pas");
        }
        fournisseurRepository.deleteById((long) id);
    }

}
