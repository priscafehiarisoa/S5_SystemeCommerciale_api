package s5_systemecommerciale_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5_systemecommerciale_api.repository.ServiceRepository;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    public List<s5_systemecommerciale_api.model.Service> getAllService()
    {
        return serviceRepository.findAll();
    }
}
