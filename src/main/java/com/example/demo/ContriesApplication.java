package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Country;
import com.example.demo.repositories.CountryRepository;

@SpringBootApplication
public class ContriesApplication implements CommandLineRunner {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ContriesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
      countryRepository.save(new Country("Cameroun","Yaoundé"));
      countryRepository.save(new Country("China","Peking"));
      countryRepository.save(new Country("German","Berlin"));
      countryRepository.save(new Country("Russia","Moscow"));
      	
	}
	
	
}
