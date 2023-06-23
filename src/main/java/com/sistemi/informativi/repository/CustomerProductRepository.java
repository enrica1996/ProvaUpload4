package com.sistemi.informativi.repository;

import java.util.List;

import com.sistemi.informativi.entity.Customer;
import com.sistemi.informativi.entity.Product;

public interface CustomerProductRepository {

	public void addCustomer(Customer customer);

	public void addProduct(Product product);

	public Customer findCustomerById(int id);

	public Product findProductById(int id);

	public List<Customer> findAllCustomersByProduct(Product product);

	public List<Product> findAllProductsByCustomer(Customer customer);

}
