package com.email.sender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.sender.dto.ContactFormDto;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.email.to}")
    private String toEmail;
    
    public void sendContactEmail(ContactFormDto contactFormDto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail); // Correo remitente (configurado en application.properties)
            message.setTo(toEmail); // Correo destinatario (configurado por ti)
            
            // Asunto del correo que recibirás
            message.setSubject("Nuevo mensaje de contacto: " + contactFormDto.getPhone());
            
            // Cuerpo del mensaje
            String emailBody = "Has recibido un nuevo mensaje de contacto:\n\n" +
                               "Nombre: " + contactFormDto.getName() + "\n" +
                               "Email: " + contactFormDto.getEmail() + "\n" +
                               "Telefono: " + contactFormDto.getPhone() + "\n" +
                               "Mensaje:\n" + contactFormDto.getMessage();
            message.setText(emailBody);
            
            mailSender.send(message);
            System.out.println("Correo de contacto enviado exitosamente a " + toEmail);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo de contacto: " + e.getMessage());
            // Considera un manejo de errores más robusto para producción
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}
