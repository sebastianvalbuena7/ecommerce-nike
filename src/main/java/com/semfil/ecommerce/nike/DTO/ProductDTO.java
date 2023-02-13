package com.semfil.ecommerce.nike.DTO;

import com.semfil.ecommerce.nike.Models.CategoryShoes;
import com.semfil.ecommerce.nike.Models.Product;

import java.util.List;

public class ProductDTO {
    private Long id;
    private int price;
    private String name,description, image;
    private CategoryShoes categoryShoes;
    private List<Integer> sizeShoes;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.categoryShoes = product.getCategoryShoes();
        this.sizeShoes = product.getSizeShoes();
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public CategoryShoes getCategoryShoes() {
        return categoryShoes;
    }

    public List<Integer> getSizeShoes() {
        return sizeShoes;
    }
}
