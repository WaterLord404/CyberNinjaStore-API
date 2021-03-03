package com.cyberninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = { "com.cyberninja" })
public class CyberNinjaStoreApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(CyberNinjaStoreApplication.class, args);
	}

}
