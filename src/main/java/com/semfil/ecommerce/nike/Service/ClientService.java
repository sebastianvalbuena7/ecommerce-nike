package com.semfil.ecommerce.nike.Service;

import com.semfil.ecommerce.nike.Models.Client;

import java.util.List;

public interface ClientService {
    void saveClient(Client client);
    List<Client> getClients();
    Client getClient(Long id);
    Client findByEmail(String email);
}
