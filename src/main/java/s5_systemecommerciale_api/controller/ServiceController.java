package s5_systemecommerciale_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import s5_systemecommerciale_api.model.Fournisseur;
import s5_systemecommerciale_api.model.Service;
import s5_systemecommerciale_api.repository.ServiceRepository;
import s5_systemecommerciale_api.service.ServiceService;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ServiceService service;

    @GetMapping
    public List<Service> getAllService(){
        return service.getAllService();
    }

    @PostMapping
    public void save(@RequestBody Service service){

        serviceRepository.save(service);
    }
}
