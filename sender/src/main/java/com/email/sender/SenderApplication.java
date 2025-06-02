package com.email.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class SenderApplication {

	 public static void main(String[] args) {
        // --- INICIA CÓDIGO PARA CARGAR .env ---
        // Carga el archivo .env desde la raíz del proyecto
        Dotenv dotenv = Dotenv.configure().load();

        // Itera sobre las entradas del .env y las establece como propiedades del sistema
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
        // --- TERMINA CÓDIGO PARA CARGAR .env ---

        SpringApplication.run(SenderApplication.class, args);
    }

}
