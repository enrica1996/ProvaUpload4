package com.sistemi.informativi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sistemi.informativi.entity.Customer;
import com.sistemi.informativi.entity.Product;
import com.sistemi.informativi.repository.CustomerProductRepository;

@Service
public class CustomerProductServiceImpl implements CustomerProductService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerProductRepository customerProductRepository;

	@Value("${message.customer.success}")
	private String customerSuccess;
	@Value("${message.customer.error}")
	private String customerError;

	@Value("${message.product.success}")
	private String productSuccess;
	@Value("${message.product.error}")
	private String productError;

	@Value("${message.customers.list.error}")
	private String customersListError;
	@Value("${message.products.list.error}")
	private String productsListError;

	@Value("${message.customer.find.error}")
	private String customerFindError;
	@Value("${message.product.find.error}")
	private String productFindError;

	@Override
	public void checkAddCustomer(Customer customer) {
		try {
			customerProductRepository.addCustomer(customer);
			log.info(customerSuccess);
		} catch (IllegalArgumentException | EntityExistsException | TransactionRequiredException e) {
			e.printStackTrace();
			log.info(customerError);
		}

	}

	@Override
	public void checkAddProduct(Product product) {
		try {
			customerProductRepository.addProduct(product);
			log.info(productSuccess);
		} catch (IllegalArgumentException | EntityExistsException | TransactionRequiredException e) {
			e.printStackTrace();
			log.info(productError);
		}
	}

	@Override
	public void checkFindAllCustomersByProduct(int productId) {

		List<Customer> customers = new ArrayList<>();

		Product product = customerProductRepository.findProductById(productId);

		if (product != null) {
			customers = customerProductRepository.findAllCustomersByProduct(product);
			if (!customers.isEmpty()) {

				customers.forEach(customer -> log.info(customer.toString()));
			} else {
				log.info(customersListError);
			}
		} else {
			log.info(productFindError);
		}

	}

	@Override
	public void checkFindAllProductsByCustomer(int customerId) {

		List<Product> products = new ArrayList<>();

		Customer customer = customerProductRepository.findCustomerById(customerId);

		if (customer != null) {
			products = customerProductRepository.findAllProductsByCustomer(customer);
			if (!products.isEmpty()) {

				products.forEach(product -> log.info(product.toString()));
			} else {
				log.info(productsListError);
			}
		} else {
			log.info(customerFindError);
		}
	}

}
