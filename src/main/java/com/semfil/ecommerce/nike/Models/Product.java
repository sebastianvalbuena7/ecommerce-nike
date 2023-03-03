package com.semfil.ecommerce.nike.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;
    private int price;
    private String name,description, image;
    private int stock;
    private CategoryShoes categoryShoes;
    private String collection;
    @ElementCollection
    private List<Integer> sizeShoes = new ArrayList<>();
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<ClientProduct> clientProducts = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentClient paymentClient;

    public Product() {
    }

    public Product(int price, String name, String description, String image, CategoryShoes categoryShoes, List<Integer> sizeShoes, int stock, String collection) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
        this.categoryShoes = categoryShoes;
        this.sizeShoes = sizeShoes;
        this.stock = stock;
        this.collection = collection;
    }

    public PaymentClient getPaymentClient() {
        return paymentClient;
    }

    public void setPaymentClient(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Set<ClientProduct> getClientProducts() {
        return clientProducts;
    }

    public void setClientProducts(Set<ClientProduct> clientProducts) {
        this.clientProducts = clientProducts;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryShoes getCategoryShoes() {
        return categoryShoes;
    }

    public void setCategoryShoes(CategoryShoes categoryShoes) {
        this.categoryShoes = categoryShoes;
    }

    public List<Integer> getSizeShoes() {
        return sizeShoes;
    }

    public void setSizeShoes(List<Integer> sizeShoes) {
        this.sizeShoes = sizeShoes;
    }
}
