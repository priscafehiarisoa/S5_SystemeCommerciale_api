package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.repository.BesoinRepository;

import java.util.List;

@Service
public class BesoinService {
    @Autowired
    BesoinRepository besoinRepository;

    public List<Besoin> getAllBesoin(){
        return besoinRepository.findAll();
    }

    public void addNewBesoin(Besoin besoin){
        besoinRepository.save(besoin);
    }
}
