package s5_systemecommerciale_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import s5_systemecommerciale_api.Reste;
import s5_systemecommerciale_api.model.stock.MouvementSortie;
import s5_systemecommerciale_api.model.stock.SortieStock;
import s5_systemecommerciale_api.model.stock.VStock;
import s5_systemecommerciale_api.repository.MouvementSortieRepository;
import s5_systemecommerciale_api.repository.ResteRepository;
import s5_systemecommerciale_api.repository.SortieStockRepository;
import s5_systemecommerciale_api.repository.VStockRepository;

import java.util.List;

@RestController
public class SortieMagasinController {

    private final SortieStockRepository sortieStockRepository;
    private final VStockRepository vStockRepository;
    private final MouvementSortieRepository mouvementSortieRepository;
    private final ResteRepository resteRepository;

    public SortieMagasinController(SortieStockRepository sortieStockRepository,
                                   VStockRepository vStockRepository,
                                   MouvementSortieRepository mouvementSortieRepository,
                                   ResteRepository resteRepository) {
        this.sortieStockRepository = sortieStockRepository;
        this.vStockRepository = vStockRepository;
        this.mouvementSortieRepository = mouvementSortieRepository;
        this.resteRepository = resteRepository;
    }

    @GetMapping("/sortieStock")
    public void sortieStock(@RequestBody SortieStock sortieStock){
        sortieStockRepository.save(sortieStock);
        List<MouvementSortie> liste=sortieStock.decomposition(vStockRepository);
        mouvementSortieRepository.saveAll(liste);
    }
    @GetMapping("/etatDeStock")
    public List<Reste> getEtatDeStock(){
        return resteRepository.findAll();
    }


}
