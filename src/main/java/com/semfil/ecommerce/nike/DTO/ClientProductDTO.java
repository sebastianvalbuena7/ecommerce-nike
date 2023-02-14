package com.semfil.ecommerce.nike.DTO;

import com.semfil.ecommerce.nike.Models.ClientProduct;

import java.time.LocalDateTime;

public class ClientProductDTO {
    private Long id;
    private LocalDateTime localDateTime;
    private int quantity;

    public ClientProductDTO(ClientProduct clientProduct) {
        this.id = clientProduct.getId();
        this.localDateTime = clientProduct.getLocalDateTime();
        this.quantity = clientProduct.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public int getQuantity() {
        return quantity;
    }
}
