package com.semfil.ecommerce.nike.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PaymentClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private LocalDateTime localDateTime;
    private Integer totalAmount;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "paymentClient")
    private Set<Product> products = new HashSet<>();

    public PaymentClient() {
    }

    public PaymentClient(LocalDateTime localDateTime, Integer totalAmount, Client client, Set<Product> products) {
        this.localDateTime = localDateTime;
        this.totalAmount = totalAmount;
        this.client = client;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}