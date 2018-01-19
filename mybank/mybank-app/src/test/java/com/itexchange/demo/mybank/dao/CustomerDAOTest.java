package com.itexchange.demo.mybank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerDAOTest {

	private static final String DEFAULT_CUSTOMER_EMAIL = "myemail@domain.com";
	private static final String DEFAULT_CUSTOMER_NAME = "name";
	private static final String DEFAULT_CUSTOMER_SURNAME = "surname";
	private static final String DEFAULT_CUSTOMER_MOBILE = "3009990099";
	private static final String DEFAULT_CUSTOMER_PASSWORD = "1234";
	private static final String DEFAULT_CUSTOMER_PHONE = "+5745500000";

	@Autowired
	private TestEntityManager testEntityManager;

	private CustomerDAO customerDAO;

	@Before
	public void before() {
		customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(testEntityManager.getEntityManager());
	}

	@Test
	public void testSave() {
		String customerId = "" + new Random().nextInt(100000);
		Customer customer = Customer.builder()
				.customerId(customerId)
				.email(DEFAULT_CUSTOMER_EMAIL)
				.mobile(DEFAULT_CUSTOMER_MOBILE)
				.name(DEFAULT_CUSTOMER_NAME)
				.password(DEFAULT_CUSTOMER_PASSWORD)
				.phone(DEFAULT_CUSTOMER_PHONE)
				.surname(DEFAULT_CUSTOMER_SURNAME)
				.build();

		customerDAO.save(customer);

		// Buscar empleado almacenado ...
		Customer found = customerDAO.findByCustomerId(customerId);
		assertThat(found.getName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
		assertThat(found.getCustomerId()).isEqualTo(customerId);
	}
}
