package com.email.sender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.email.sender.dto.ContactFormDto;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

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
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            // Nombre que verá el cliente en "De"
            helper.setFrom(new InternetAddress(fromEmail, contactFormDto.getName()));

            helper.setTo(toEmail);

            // Para que al responder vaya al correo de la persona
            helper.setReplyTo(contactFormDto.getEmail());
            helper.setSubject("Neue Kontaktanfrage: " + contactFormDto.getPhone());

            String emailBody = "Sie haben eine neue Kontaktanfrage erhalten:\n\n" +
                            "Name: " + contactFormDto.getName() + "\n" +
                            "E-Mail: " + contactFormDto.getEmail() + "\n" +
                            "Telefon: " + contactFormDto.getPhone() + "\n" +
                            "Nachricht:\n" + contactFormDto.getMessage();

            helper.setText(emailBody, false);

            mailSender.send(message);
            System.out.println("Correo de contacto enviado exitosamente a " + toEmail);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo de contacto: " + e.getMessage());
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}
