package com.semfil.ecommerce.nike.Controllers;

import com.semfil.ecommerce.nike.DTO.ClientDTO;
import com.semfil.ecommerce.nike.DTO.NewClientDTO;
import com.semfil.ecommerce.nike.Models.Client;
import com.semfil.ecommerce.nike.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/newClient")
    public ResponseEntity<Object> newClient(@RequestBody NewClientDTO newClientDTO) {
        Client client = new Client(newClientDTO.getFirstName(), newClientDTO.getLastName(), newClientDTO.getEmail(), newClientDTO.getPassword(), LocalDate.now());
        clientService.saveClient(client);
        return new ResponseEntity<>("Client saved", HttpStatus.OK);
    }

    @GetMapping("/getClients")
    public List<ClientDTO> getClients() {
        return clientService.getClients().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/getClients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return new ClientDTO(clientService.getClient(id));
    }
}
