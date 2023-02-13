package com.semfil.ecommerce.nike.Service.Implementations;

import com.semfil.ecommerce.nike.Models.Product;
import com.semfil.ecommerce.nike.Repositories.ProductRepository;
import com.semfil.ecommerce.nike.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Set<Product> getProducts() {
        return productRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
