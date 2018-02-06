package com.itexchange.demo.mybank.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.CustomerProduct;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerProductDAOTest {

	@Autowired
	private TestEntityManager testEntityManager;

	private CustomerProductDAO customerProductDAO;
	
	private CustomerDAO customerDAO;

	@Before
	public void before() {
		customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(testEntityManager.getEntityManager());
		customerProductDAO = new CustomerProductDAO(customerDAO);
		customerProductDAO.setEntityManager(testEntityManager.getEntityManager());
	}

	@Test
	public void testGetCustomerProducts() {
		log.info("Entity manager: " + testEntityManager.getEntityManager());
		List<CustomerProduct> customerProducts = customerProductDAO.getCustomerProducts("1012345");

		assertThat(customerProducts).isNotEmpty();
		assertThat(customerProducts.get(0)).isNotNull();
		assertThat(customerProducts.get(0).getCustomer()).isNotNull();
		assertThat(customerProducts.get(0).getProductNumber()).isEqualTo("1000000001");
	}
}
