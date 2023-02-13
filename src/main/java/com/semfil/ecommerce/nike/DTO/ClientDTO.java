package com.semfil.ecommerce.nike.DTO;

import com.semfil.ecommerce.nike.Models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String firstName, lastName, email;
    private Set<ProductDTO> products;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.products = client.getProducts().stream().map(ProductDTO::new).collect(Collectors.toSet());
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

    public Set<ProductDTO> getProducts() {
        return products;
    }
}
