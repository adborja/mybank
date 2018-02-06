package com.itexchange.demo.jpa;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.itexchange.demo.jpa.dao.CustomerDAO;
import com.itexchange.demo.jpa.dao.CustomerProductDAO;
import com.itexchange.demo.jpa.dao.ProductDAO;
import com.itexchange.demo.jpa.dao.impl.CustomerDAOImpl;
import com.itexchange.demo.jpa.dao.impl.CustomerProductDAOImpl;
import com.itexchange.demo.jpa.dao.impl.ProductDAOImpl;
import com.itexchange.demo.jpa.domain.Customer;
import com.itexchange.demo.jpa.domain.CustomerProduct;
import com.itexchange.demo.jpa.domain.Product;
import com.itexchange.demo.jpa.exception.ObjectNotFoundException;

public class Main {

	public static void main(String[] args) {
		try {
			CustomerDAO customerDAO = new CustomerDAOImpl();
			Customer customer = customerDAO.findByCustomerId("1012345");
			System.out.println("Customer found: " + customer.getName());

			// Creating new customer
			Customer newCustomer = new Customer();
			newCustomer.setCustomerId("7012345");
			newCustomer.setEmail("brian.may@queenonline.com");
			newCustomer.setMobile("3119990044");
			newCustomer.setName("Brian");
			newCustomer.setSurname("May");
			newCustomer.setPhone("+5749990044");
			newCustomer.setPassword("secret");

			Customer saved = customerDAO.save(newCustomer);
			System.out.println("New customer saved. " + saved);
			
			// Creating new product for customer
			ProductDAO productDAO = new ProductDAOImpl();
			Product savings = productDAO.findById(1);
			
			Customer theCustomer = customerDAO.findByCustomerId("7012345");
			
			CustomerProduct customerProduct = new CustomerProduct();
			customerProduct.setBalance(BigDecimal.valueOf(35000));
			customerProduct.setCreationDate(new Timestamp(System.currentTimeMillis()));
			customerProduct.setCustomer(theCustomer);
			customerProduct.setProduct(savings);
			customerProduct.setProductNumber("1000000004");
			customerProduct.setStatus("ACTIVE");
			
			CustomerProductDAO customerProductDAO = new CustomerProductDAOImpl();
			customerProductDAO.save(customerProduct);
			System.out.println("Product for customer created. " + customerProduct);
			

		} catch (ObjectNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
