package com.sistemi.informativi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistemi.informativi.entity.Customer;
import com.sistemi.informativi.entity.Product;
import com.sistemi.informativi.service.CustomerProductService;

@SpringBootApplication
public class SbJpaManytomanyCrudApplication implements CommandLineRunner {

	@Autowired
	private CustomerProductService customerProductService;

	public static void main(String[] args) {
		SpringApplication.run(SbJpaManytomanyCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * inizializziamo le liste in maniera che poi potremo fare l'associazione tra un
		 * customer e una lista di products e viceversa.
		 */
		List<Customer> customers = new ArrayList<>();
		List<Product> products = new ArrayList<>();

		Customer customer1 = new Customer("Mario Rossi", "Italy");
		Customer customer2 = new Customer("Rina Rana", "Italy");
		
		customers.add(customer1);
		customers.add(customer2);

		Product product1 = new Product("Smartphone", "China");
		Product product2 = new Product("Lavatrice", "Italy");
		
		products.add(product1);
		products.add(product2);

		customer1.setProducts(products);
		customer2.setProducts(products);

		product1.setCustomers(customers);
		product2.setCustomers(customers);

		customerProductService.checkAddCustomer(customer1);
		customerProductService.checkAddCustomer(customer2);
		customerProductService.checkAddProduct(product1);
		customerProductService.checkAddProduct(product2);
		//ricerca di tutti i customers associati al product con id=1/2
		customerProductService.checkFindAllCustomersByProduct(1);
		customerProductService.checkFindAllCustomersByProduct(2);
		//ricerca di tutti i products associati al customer con id=1/2
		customerProductService.checkFindAllProductsByCustomer(1);
		customerProductService.checkFindAllProductsByCustomer(2);
	}

}
