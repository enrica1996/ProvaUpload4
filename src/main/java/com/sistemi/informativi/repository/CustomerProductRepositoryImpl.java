package com.sistemi.informativi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistemi.informativi.entity.Customer;
import com.sistemi.informativi.entity.Product;

@Repository
@Transactional
public class CustomerProductRepositoryImpl implements CustomerProductRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addCustomer(Customer customer) {
		em.persist(customer);

	}

	@Override
	public void addProduct(Product product) {
		em.persist(product);

	}

	@Override
	public Customer findCustomerById(int id) {
		return em.find(Customer.class, id);
	}

	@Override
	public Product findProductById(int id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Customer> findAllCustomersByProduct(Product product) {
		return product.getCustomers();
	}

	@Override
	public List<Product> findAllProductsByCustomer(Customer customer) {
		return customer.getProducts();
	}

}
