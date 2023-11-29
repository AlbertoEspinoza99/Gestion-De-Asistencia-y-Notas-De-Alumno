package com.colegio;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.colegio.dao.CargoRepository;
import com.colegio.dao.PersonaRepository;
import com.colegio.entity.Cargo;
import com.colegio.entity.Persona;
import com.colegio.services.lmpl.EmailServiceImpl;

@SpringBootApplication
public class ColegioApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(ColegioApplication.class, args);
	}
/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("/**").allowedMethods("*").allowedHeaders("*");
			}
		};
	}
	*/
	
	
	@Bean
	CommandLineRunner run(CargoRepository cargoRepository,PersonaRepository personaRepository,PasswordEncoder passwordencoder) {
		return args ->{
			if(cargoRepository.findByAuthority("ADMIN").isPresent()) return;
			Cargo adminRole = cargoRepository.save(new Cargo("ADMIN"));
			cargoRepository.save(new Cargo("USER"));

			Set<Cargo> roles = new HashSet<>();
			roles.add(adminRole);

			Persona admin = new Persona(1, "admin", passwordencoder.encode("password"), null, null, 0, null, null, 0, null, null, roles);

			personaRepository.save(admin);
		
	};
	
	
	}
	
}