package com.example.OpenList;

import com.example.OpenList.accessingdatajpa.Customer;
import com.example.OpenList.accessingdatajpa.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class OpenListApplication {
	private static final Logger log = LoggerFactory.getLogger(OpenListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OpenListApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer", "Chennai"));
			repository.save(new Customer("Chloe", "O'Brian", "Chennai"));
			repository.save(new Customer("Kim", "Bauer", "Chennai"));
			repository.save(new Customer("David", "Palmer", "Chennai"));
			repository.save(new Customer("Michelle", "Dessler", "Chennai"));

			repository.save(new Customer("Jack", "Bauer", "Mumbai"));
			repository.save(new Customer("Chloe", "O'Brian", "Mumbai"));


			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(customer -> log.info(customer.toString()));
			log.info("");

			// fetch specific criteria
			List<String> column1Values = List.of("Jack", "Kim");
			List<String> column2Values = List.of("Bauer", "Bauer");

			List<Customer> results = repository.findByTuples(
					column1Values.toArray(new String[0]),
					column2Values.toArray(new String[0])
			);

			log.info("Customers found with findByTuples():");
			log.info("-------------------------------");
			results.forEach(customer -> log.info(customer.toString()));
			log.info("");

		};
	}

}
