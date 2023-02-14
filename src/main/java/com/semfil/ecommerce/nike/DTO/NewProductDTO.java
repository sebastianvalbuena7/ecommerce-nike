package com.semfil.ecommerce.nike.DTO;

import com.semfil.ecommerce.nike.Models.CategoryShoes;

public class NewProductDTO {
    private int price;
    private String name,description, image;
    private int stock;
    private CategoryShoes categoryShoes;
    private Integer[] sizeShoes;
    private String collection;

    public NewProductDTO(int price, String name, String description, String image, CategoryShoes categoryShoes, Integer[] sizeShoes, int stock, String collection) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
        this.categoryShoes = categoryShoes;
        this.sizeShoes = sizeShoes;
        this.stock = stock;
        this.collection = collection;
    }

    public String getCollection() {
        return collection;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public CategoryShoes getCategoryShoes() {
        return categoryShoes;
    }

    public Integer[] getSizeShoes() {
        return sizeShoes;
    }
}
