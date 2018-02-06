package com.itexchange.demo.mybank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.CompanyCustomer;
import com.itexchange.demo.mybank.domain.Customer;
import com.itexchange.demo.mybank.domain.CustomerProduct;
import com.itexchange.demo.mybank.domain.EmployeeCustomer;
import com.itexchange.demo.mybank.domain.Product;
import com.itexchange.demo.mybank.domain.dto.CustomerNames;

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

	private ProductDAO productDAO;

	@Before
	public void before() {
		customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(testEntityManager.getEntityManager());
		productDAO = new ProductDAO();
		productDAO.setEntityManager(testEntityManager.getEntityManager());
	}

	@Test
	public void testSave() {
		String customerId = "" + new Random().nextInt(100000);
		Customer customer = Customer.builder().customerId(customerId).email(DEFAULT_CUSTOMER_EMAIL)
				.mobile(DEFAULT_CUSTOMER_MOBILE).name(DEFAULT_CUSTOMER_NAME).password(DEFAULT_CUSTOMER_PASSWORD)
				.phone(DEFAULT_CUSTOMER_PHONE).surname(DEFAULT_CUSTOMER_SURNAME).build();

		customerDAO.save(customer);

		// Buscar empleado almacenado ...
		Customer found = customerDAO.findByCustomerId(customerId);
		assertThat(found.getName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
		assertThat(found.getCustomerId()).isEqualTo(customerId);
	}

	@Test
	public void testGetCustomerNames() {
		List<CustomerNames> customerNames = customerDAO.findCustomerNames();
		assertThat(customerNames).isNotEmpty();
		assertThat(customerNames.get(0).getName()).isEqualTo("John");
		assertThat(customerNames.get(0).getSurname()).isEqualTo("Lydon");
	}

	@Test
	public void testFindCustomersWithMoreThan() {
		// Getting one customer
		Customer customer = customerDAO.findByCustomerId("3012345");
		
		// Getting a product
		Product product = productDAO.findByPrimaryKey(2);
		
		// Creating product for customer
		CustomerProduct cp = CustomerProduct.builder()
				.balance(BigDecimal.ZERO)
				.creationDate(new Timestamp(System.currentTimeMillis()))
				.customer(customer)
				.product(product)
				.productNumber("1000000004")
				.status("ACTIVE")
				.build();
		CustomerProductDAO customerProductDAO = new CustomerProductDAO(customerDAO);
		customerProductDAO.setEntityManager(testEntityManager.getEntityManager());
		customerProductDAO.save(cp);
		
		List<Customer> customers = customerDAO.findCustomersWithMoreThan(2l);
		assertThat(customers).isNotEmpty();
		assertThat(customers.size()).isEqualTo(1);
		assertThat(customers.get(0).getName()).isEqualTo("David");
	}
	
	@Test
	public void testFindCompanyCustomerByCompanyId() {
		String companyId = "4005002";
		CompanyCustomer customer = customerDAO.findCompanyCustomerByCompanyId(companyId);
		
		assertThat(customer).isNotNull();
		assertThat(customer.getCompanyId()).isEqualTo(companyId);
		assertThat(customer.getName()).isEqualTo("itexchange.com");
		assertThat(customer.getPhone()).isEqualTo("+5745110091");
	}
	
	@Test
	public void testFindEmployeeCustomerByEmployeeId() {
		String employeeId = "90104";
		EmployeeCustomer customer = customerDAO.findEmployeeCustomerByEmployeeId(employeeId);
		
		assertThat(customer).isNotNull();
		assertThat(customer.getEmployeeId()).isEqualTo(employeeId);
		assertThat(customer.getName()).isEqualTo("Thomas");
		assertThat(customer.getSurname()).isEqualTo("Williams");
		assertThat(customer.getEmail()).isEqualTo("thomas.williams@mybank.com");
		assertThat(customer.getMobile()).isEqualTo("+57301000004");
	}
}
