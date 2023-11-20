package s5_systemecommerciale_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Besoin;
import s5_systemecommerciale_api.model.Proforma;
import s5_systemecommerciale_api.repository.BesoinRepository;
import s5_systemecommerciale_api.service.ProformaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/besoin")
public class ProformaController {
    private final ProformaService proformaService;
    private final BesoinRepository besoinRepository;

    public ProformaController(ProformaService proformaService, BesoinRepository besoinRepository) {
        this.proformaService = proformaService;
        this.besoinRepository = besoinRepository;
    }

//    @GetMapping("getBesoin/{id}")
//    @Transactional
//    public ResponseEntity<?> getProforma(@PathVariable Long id){
//        Proforma prof=new Proforma();
//        try{
//            Optional<Besoin> besoin= besoinRepository.findById(id);
//
//            if (besoin.isPresent()) {
//                prof= proformaService.getProforma(besoin.get());
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return new ResponseEntity<>(prof.toString(), HttpStatus.OK);
//    }
}
