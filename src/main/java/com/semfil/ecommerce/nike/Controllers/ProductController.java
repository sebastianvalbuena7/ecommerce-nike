package com.semfil.ecommerce.nike.Controllers;

import com.semfil.ecommerce.nike.DTO.NewProductDTO;
import com.semfil.ecommerce.nike.DTO.ProductDTO;
import com.semfil.ecommerce.nike.Models.Client;
import com.semfil.ecommerce.nike.Models.ClientProduct;
import com.semfil.ecommerce.nike.Models.Product;
import com.semfil.ecommerce.nike.Service.ClientProductService;
import com.semfil.ecommerce.nike.Service.ClientService;
import com.semfil.ecommerce.nike.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientProductService clientProductService;

    @PostMapping("/newProduct")
    public ResponseEntity<Object> newProduct(Authentication authentication ,@RequestBody NewProductDTO newProductDTO) {
        Client client = clientService.findByEmail(authentication.getName());
        if(!Objects.equals(client.getEmail(), "admin@correo.com")) {
            return new ResponseEntity<>("You are not admin", HttpStatus.FORBIDDEN);
        }
        List<Integer> sizeShoes = Arrays.stream(newProductDTO.getSizeShoes()).collect(Collectors.toList());
        Product product = new Product(newProductDTO.getPrice(), newProductDTO.getName(), newProductDTO.getDescription(), newProductDTO.getImage(), newProductDTO.getCategoryShoes(), sizeShoes, newProductDTO.getStock(), newProductDTO.getCollection());
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

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted!", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getProducts")
    public Set<ProductDTO> getProducts() {
        return productService.getProducts().stream().map(ProductDTO::new).collect(Collectors.toSet());
    }

    @PostMapping("/addProductCart")
    public ResponseEntity<Object> addProductCart(Authentication authentication, @RequestParam int quantity, @RequestParam Long id) {
        Client client = clientService.findByEmail(authentication.getName());
        Product product = productService.getProduct(id);
        ClientProduct clientProduct = new ClientProduct(LocalDateTime.now(), quantity, client, product);
        clientProductService.saveClientProduct(clientProduct);
        return new ResponseEntity<>("Product saved", HttpStatus.ACCEPTED);
    }

    @GetMapping("/productsClient")
    public Set<ProductDTO> productsClient(Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        Set<Product> productsClient = client.getClientProducts().stream().map(clientProduct -> productService.getProduct(clientProduct.getId())).collect(Collectors.toSet());
        return productsClient.stream().map(ProductDTO::new).collect(Collectors.toSet());
    }
}