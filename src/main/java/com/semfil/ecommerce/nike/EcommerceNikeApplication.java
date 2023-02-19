package com.semfil.ecommerce.nike;

import com.semfil.ecommerce.nike.Models.CategoryShoes;
import com.semfil.ecommerce.nike.Models.Client;
import com.semfil.ecommerce.nike.Models.Product;
import com.semfil.ecommerce.nike.Service.ClientService;
import com.semfil.ecommerce.nike.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class EcommerceNikeApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceNikeApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(ClientService clientService, ProductService productService) {
		return (args) -> {
			Client sebas = new Client("Sebastian", "Valbuena", "sebastianvalbuena25@gmail.com", passwordEncoder.encode("sebas123"), LocalDate.now());
			clientService.saveClient(sebas);

			Client fiore = new Client("admin", "admin", "admin@correo.com", passwordEncoder.encode("admin"), LocalDate.now());
			clientService.saveClient(fiore);

			Product product1 = new Product(3500, "Jordan Air Classic", "This shoes are incredible", "https://i.ibb.co/Fgr5xPz/shoes-red-and-black.png", CategoryShoes.MEN, List.of(1,2,4), 5, "Primavera");
			productService.saveProduct(product1);
			Product product2 = new Product(1800, "Nike Sport Blue", "This shoes are blue", "https://i.ibb.co/TWbsv4C/shoes-header.png", CategoryShoes.WOMEN, List.of(2,4,6), 15, "Otonio");
			productService.saveProduct(product2);
			Product product3 = new Product(1200, "Nike She Pink", "This shoes are pink", "https://i.ibb.co/TvXS1B1/pink-nike.png", CategoryShoes.WOMEN, List.of(2,4,6,7), 18, "Verano");
			productService.saveProduct(product3);
			Product product4 = new Product(800, "Nike Yellow", "This shoes are yellow and black", "https://i.ibb.co/ZddN9DY/yellow-nike.png", CategoryShoes.MEN, List.of(2,4), 8, "Verano");
			productService.saveProduct(product4);
			Product product5 = new Product(500, "Nike Black", "This shoes are black", "https://i.ibb.co/F7Z4cDW/black-nike-removebg-preview.png", CategoryShoes.MEN, List.of(2,4), 8, "Otonio");
			productService.saveProduct(product5);
		};
	}
}