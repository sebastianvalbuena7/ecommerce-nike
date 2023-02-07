package com.semfil.ecommerce.nike.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;
    private int price;
    private String name,description, image;
    private CategoryShoes categoryShoes;
    @ElementCollection
    private List<Integer> sizeShoes = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public Product() {
    }

    public Product(int price, String name, String description, String image, CategoryShoes categoryShoes, List<Integer> sizeShoes) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
        this.categoryShoes = categoryShoes;
        this.sizeShoes = sizeShoes;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
