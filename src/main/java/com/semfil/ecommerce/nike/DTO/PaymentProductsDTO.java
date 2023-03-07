package com.semfil.ecommerce.nike.DTO;

import com.semfil.ecommerce.nike.Models.Product;

public class PaymentProductsDTO {
    private int quantity;
    private Product product;

    public PaymentProductsDTO(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}