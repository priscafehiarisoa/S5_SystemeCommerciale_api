package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import s5_systemecommerciale_api.repository.ServiceRepository;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/service")
    public List<s5_systemecommerciale_api.model.elses.Service> getAllService()
    {
        return serviceRepository.findAll();
    }
}
