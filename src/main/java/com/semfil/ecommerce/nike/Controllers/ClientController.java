package com.semfil.ecommerce.nike.Controllers;

import com.semfil.ecommerce.nike.DTO.ClientDTO;
import com.semfil.ecommerce.nike.DTO.NewClientDTO;
import com.semfil.ecommerce.nike.Models.Client;
import com.semfil.ecommerce.nike.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestParam String emailDestiny) {
        if(emailDestiny == null || emailDestiny.equals("")) {
            return new ResponseEntity<>("Email no valido", HttpStatus.FORBIDDEN);
        }
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDestiny);
        email.setFrom("sebastianproyectosit@gmail.com");
        email.setSubject("Now you will receive emails from Nike with updates and offers😎✌️");
        email.setText("Dear user,\n" +
                "\n" +
                "We are pleased to inform you that from now on you will receive the latest news and updates from the Nike shoe store directly in your inbox. We will keep you updated on new releases, exclusive offers and special events so that you are always in the know about the latest in sports fashion.\n" +
                "\n" +
                "At Nike, we strive to provide our customers with the best shopping experience possible, and part of that involves keeping you informed about everything that's happening in our store. So we hope you enjoy these updates and find the information useful and interesting.\n" +
                "\n" +
                "If at any time you wish to unsubscribe from this news, you may do so at any time by clicking on the unsubscribe link at the bottom of any email you receive from us.\n" +
                "\n" +
                "Thank you for your loyalty and trust in the Nike brand. We look forward to keeping you updated and helping you find the perfect athletic shoe for your needs.\n" +
                "\n" +
                "Sincerely,\n" +
                "The Nike Team\n");
        javaMailSender.send(email);
        return new ResponseEntity<>("Email send!", HttpStatus.ACCEPTED);
    }

    @PostMapping("/newClient")
    public ResponseEntity<Object> newClient(@RequestBody NewClientDTO newClientDTO) {
        Client client = clientService.findByEmail(newClientDTO.getEmail());
        if(newClientDTO.getFirstName().isEmpty() && newClientDTO.getLastName().isEmpty()) {
            return new ResponseEntity<>("Empty fields", HttpStatus.FORBIDDEN);
        }
        if(newClientDTO.getFirstName() == null || newClientDTO.getFirstName().equals("") || newClientDTO.getFirstName().isEmpty()) {
            return new ResponseEntity<>("No there is name", HttpStatus.FORBIDDEN);
        }
        if(newClientDTO.getLastName() == null || newClientDTO.getLastName().equals("") || newClientDTO.getLastName().isEmpty()) {
            return new ResponseEntity<>("No there is lastName", HttpStatus.FORBIDDEN);
        }
        if(newClientDTO.getEmail() == null || newClientDTO.getEmail().equals("") || newClientDTO.getEmail().isEmpty()) {
            return new ResponseEntity<>("No there is email", HttpStatus.FORBIDDEN);
        }
        if(newClientDTO.getPassword() == null || newClientDTO.getPassword().equals("") || newClientDTO.getPassword().isEmpty()) {
            return new ResponseEntity<>("No there is password", HttpStatus.FORBIDDEN);
        }
        if(client != null) {
            return new ResponseEntity<>("The client already exists", HttpStatus.FORBIDDEN);
        }

        Client clientCurrent = new Client(newClientDTO.getFirstName(), newClientDTO.getLastName(), newClientDTO.getEmail(), passwordEncoder.encode(newClientDTO.getPassword()), LocalDate.now());
        clientService.saveClient(clientCurrent);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(newClientDTO.getEmail());
        email.setFrom("sebastianproyectosit@gmail.com");
        email.setSubject("You have successfully registered👾");
        email.setText("You have successfully registered on our website and now you can make as many purchases as you wish and receive weekly promotions. \n" +
                "\n" +
                "With love from the Nike team🤍");
        javaMailSender.send(email);
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

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}