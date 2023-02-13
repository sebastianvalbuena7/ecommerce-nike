package com.semfil.ecommerce.nike.Service.Implementations;

import com.semfil.ecommerce.nike.DTO.ClientDTO;
import com.semfil.ecommerce.nike.Models.Client;
import com.semfil.ecommerce.nike.Repositories.ClientRepository;
import com.semfil.ecommerce.nike.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}
