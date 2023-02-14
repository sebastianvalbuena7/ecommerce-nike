package com.semfil.ecommerce.nike.Service.Implementations;

import com.semfil.ecommerce.nike.Models.ClientProduct;
import com.semfil.ecommerce.nike.Repositories.ClientProductRepository;
import com.semfil.ecommerce.nike.Service.ClientProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientProductServiceImpl implements ClientProductService {
    @Autowired
    private ClientProductRepository clientProductRepository;

    @Override
    public void saveClientProduct(ClientProduct clientProduct) {
        clientProductRepository.save(clientProduct);
    }
}
