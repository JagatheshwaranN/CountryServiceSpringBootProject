package com.countryservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountryServiceSpringBootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryServiceSpringBootProjectApplication.class, args);
		System.out.println("This is Country Service Demo project");
	}

}
