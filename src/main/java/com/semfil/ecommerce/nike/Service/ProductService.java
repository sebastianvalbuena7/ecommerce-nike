package com.semfil.ecommerce.nike.Service;

import com.semfil.ecommerce.nike.Models.Product;

import java.util.Set;

public interface ProductService {
    void saveProduct(Product product);
    Set<Product> getProducts();
    Product getProduct(Long id);
    void deleteProduct(Long id);
}
