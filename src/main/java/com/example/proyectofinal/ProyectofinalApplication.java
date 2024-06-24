package com.example.proyectofinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.proyectofinal", "products.service", "products.repositories", "products.controller", "products.model"})
public class ProyectofinalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProyectofinalApplication.class, args);
	}
}
