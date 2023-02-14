package com.semfil.ecommerce.nike.DTO;

import com.semfil.ecommerce.nike.Models.CategoryShoes;
import com.semfil.ecommerce.nike.Models.Product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDTO {
    private Long id;
    private int price;
    private String name,description, image;
    private CategoryShoes categoryShoes;
    private List<Integer> sizeShoes;
    private int stock;

    private Set<ClientProductDTO> clientProductDTO;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.categoryShoes = product.getCategoryShoes();
        this.sizeShoes = product.getSizeShoes();
        this.stock = product.getStock();
        this.clientProductDTO = product.getClientProducts().stream().map(ClientProductDTO::new).collect(Collectors.toSet());
    }

    public Set<ClientProductDTO> getClientProductDTO() {
        return clientProductDTO;
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

    public int getStock() {
        return stock;
    }
}
