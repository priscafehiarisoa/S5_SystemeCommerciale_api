package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.fournisseur.Fournisseur;
import s5_systemecommerciale_api.repository.FournisseurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {
    @Autowired
    FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseur(){
        return fournisseurRepository.findAll();
    }

    public void addNewFournisseur(Fournisseur fournisseur){
        Fournisseur  f2 = new Fournisseur(fournisseur.getNom_fournisseur(), fournisseur.getAdresse(), fournisseur.getTelephone(),fournisseur.getNom_responsable(),fournisseur.getPrix_livraison(), fournisseur.getEmail() );
        fournisseurRepository.save(fournisseur);
    }

    public void deleteFournisseur(long id) throws IllegalAccessException {
        if(!fournisseurRepository.existsById(id))
        {
            throw new IllegalAccessException("fournisseur n existe pas");
        }
        fournisseurRepository.deleteById((long) id);
    }
    public Fournisseur getFournisseurByID(Long id) throws Exception {
        Optional<Fournisseur> f= fournisseurRepository.findById(id);
        if(f.isPresent()) return f.get();
        throw new Exception("fournisseur n*"+id+"introuvable");
    }
    public List<Fournisseur> getAllFournisseursByIDs(List<Long> ids) throws Exception {
        List<Fournisseur> fournisseurs= new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            fournisseurs.add(getFournisseurByID(ids.get(i)));
        }
        return fournisseurs;
    }



}
