package com.semfil.ecommerce.nike.DTO;

import com.semfil.ecommerce.nike.Models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String firstName, lastName, email;
    private Set<ClientProductDTO> clientProductDTO;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.clientProductDTO = client.getClientProducts().stream().map(ClientProductDTO::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<ClientProductDTO> getProducts() {
        return clientProductDTO;
    }
}
