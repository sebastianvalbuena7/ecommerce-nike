package com.semfil.ecommerce.nike.Repositories;

import com.semfil.ecommerce.nike.Models.ClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProductRepository extends JpaRepository<ClientProduct, Long> {
}
