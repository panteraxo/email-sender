package com.email.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.sender.dto.ContactFormDto;
import com.email.sender.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen
public class ContactController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> receiveContactForm(@RequestBody ContactFormDto contactFormDto) {
        // Validaciones básicas (puedes expandirlas)
        if (contactFormDto.getName() == null || contactFormDto.getName().isEmpty() ||
            contactFormDto.getEmail() == null || contactFormDto.getEmail().isEmpty() ||
            contactFormDto.getPhone() == null || contactFormDto.getPhone().isEmpty() ||
            contactFormDto.getMessage() == null || contactFormDto.getMessage().isEmpty()) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios.");
        }

        try {
            emailService.sendContactEmail(contactFormDto);
            return ResponseEntity.ok("Mensaje enviado correctamente. Gracias por contactarnos.");
        } catch (Exception e) {
            // Loggear el error e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al procesar su solicitud: " + e.getMessage());
        }
    }
    
}
