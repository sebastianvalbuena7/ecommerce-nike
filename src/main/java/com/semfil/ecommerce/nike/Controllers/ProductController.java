package com.semfil.ecommerce.nike.Controllers;

import com.semfil.ecommerce.nike.DTO.NewProductDTO;
import com.semfil.ecommerce.nike.DTO.ProductDTO;
import com.semfil.ecommerce.nike.Models.Product;
import com.semfil.ecommerce.nike.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/newProduct")
    public ResponseEntity<Object> newProduct(@RequestBody NewProductDTO newProductDTO) {
        List<Integer> sizeShoes = Arrays.stream(newProductDTO.getSizeShoes()).collect(Collectors.toList());
        Product product = new Product(newProductDTO.getPrice(), newProductDTO.getName(), newProductDTO.getDescription(), newProductDTO.getImage(), newProductDTO.getCategoryShoes(), sizeShoes);
        productService.saveProduct(product);
        return new ResponseEntity<>("Product Save", HttpStatus.ACCEPTED);
    }

    @PutMapping("/editProduct/{id}")
    public ResponseEntity<Object> editProduct(@RequestBody NewProductDTO productDTO, @PathVariable Long id) {
        List<Integer> sizeShoes = Arrays.stream(productDTO.getSizeShoes()).collect(Collectors.toList());
        Product product = productService.getProduct(id);
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setDescription(product.getDescription());
        product.setImage(productDTO.getImage());
        product.setCategoryShoes(productDTO.getCategoryShoes());
        product.setSizeShoes(sizeShoes);
        productService.saveProduct(product);
        return new ResponseEntity<>("Product Edited", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getProducts")
    public Set<ProductDTO> getProducts() {
        return productService.getProducts().stream().map(product -> new ProductDTO(product)).collect(Collectors.toSet());
    }
}