package com.semfil.ecommerce.nike.DTO;

public class PaymentProductsDTO {
    private int quantity;
    private Long id;

    public PaymentProductsDTO(int quantity, Long id) {
        this.quantity = quantity;
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}