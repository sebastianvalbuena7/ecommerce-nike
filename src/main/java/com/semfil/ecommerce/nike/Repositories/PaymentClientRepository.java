package com.semfil.ecommerce.nike.Repositories;

import com.semfil.ecommerce.nike.Models.PaymentClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaymentClientRepository extends JpaRepository<PaymentClient, Long> {
}
