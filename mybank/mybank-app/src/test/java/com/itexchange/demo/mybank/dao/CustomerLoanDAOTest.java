package com.itexchange.demo.mybank.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.CarLoan;
import com.itexchange.demo.mybank.domain.Customer;
import com.itexchange.demo.mybank.domain.CustomerLoan;
import com.itexchange.demo.mybank.domain.HomeLoan;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerLoanDAOTest {

	@Autowired
	private TestEntityManager testEntityManager;

	private CustomerDAO customerDAO;

	private CustomerLoanDAO customerLoanDAO;

	private LoanDAO loanDAO;

	@Before
	public void before() {
		customerDAO = new CustomerDAO();
		customerDAO.setEntityManager(testEntityManager.getEntityManager());
		customerLoanDAO = new CustomerLoanDAO();
		customerLoanDAO.setEntityManager(testEntityManager.getEntityManager());
		loanDAO = new LoanDAO();
		loanDAO.setEntityManager(testEntityManager.getEntityManager());
	}

	@Test
	public void testCreateLoan() {
		// Get customer by id...
		Customer customer = customerDAO.findByCustomerId("3012345");

		// Get Car loan
		CarLoan carLoan = loanDAO.findCarLoanByName("new");
		
		// Get Home loan
		HomeLoan homeLoan = loanDAO.findHomeLoanByName("used");

		// Create two loans: Car and Home
		customerLoanDAO.createLoan(customer, carLoan, BigDecimal.valueOf(20000000));
		customerLoanDAO.createLoan(customer, homeLoan, BigDecimal.valueOf(300000000));

		// Get created customer loan ...
		List<CustomerLoan> foundLoans = customerLoanDAO.getCustomerLoans(customer);

		assertThat(foundLoans).isNotEmpty();
		assertThat(foundLoans.size()).isEqualTo(2);
		assertThat(foundLoans.get(0).getLoan()).isNotNull();
		assertThat(foundLoans.get(0).getLoan().getName()).isEqualTo("new");
		assertThat(foundLoans.get(0).getLoan().getClass()).isEqualTo(CarLoan.class);
		assertThat(foundLoans.get(0).getLoanNumber()).startsWith("1100");
		
		assertThat(foundLoans.get(1).getLoan()).isNotNull();
		assertThat(foundLoans.get(1).getLoan().getName()).isEqualTo("used");
		assertThat(foundLoans.get(1).getLoan().getClass()).isEqualTo(HomeLoan.class);
		assertThat(foundLoans.get(1).getLoanNumber()).startsWith("2100");
	}
}
