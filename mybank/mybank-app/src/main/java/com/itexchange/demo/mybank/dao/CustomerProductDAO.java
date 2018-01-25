package com.itexchange.demo.mybank.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.itexchange.demo.mybank.domain.Customer;
import com.itexchange.demo.mybank.domain.CustomerProduct;

@Component
public class CustomerProductDAO extends BaseDAO {

	private CustomerDAO customerDAO;

	public CustomerProductDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public List<CustomerProduct> getCustomerProducts(String customerId) {
		// Get customer by customer id
		Customer customer = customerDAO.findByCustomerId(customerId);

		String sqlQuery = "SELECT id, customer_id, product_id, creation_date,"
				+ " termination_date, balance, status, product_number " + "FROM customer_product WHERE customer_id = ?";

		Query query = entityManager.createNativeQuery(sqlQuery, CustomerProduct.class);
		query.setParameter(1, customer.getId());
		List<CustomerProduct> customerProducts = query.getResultList();

		return customerProducts;
	}

	public List<CustomerProduct> getByProductNumber(String productNumber) {
		String sqlQuery = "SELECT id, customer_id, product_id, creation_date,"
				+ " termination_date, balance, status, product_number "
				+ "FROM customer_product WHERE product_number = :productNumber";

		Query query = entityManager.createNativeQuery(sqlQuery, CustomerProduct.class);
		query.setParameter("productNumber", productNumber);
		List<CustomerProduct> customerProducts = query.getResultList();

		return customerProducts;
	}
	
	@Transactional
	public CustomerProduct save(CustomerProduct cp) {
		entityManager.persist(cp);
		return cp;
	}
}
