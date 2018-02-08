package com.itexchange.demo.mybank.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.itexchange.demo.mybank.domain.Customer;
import com.itexchange.demo.mybank.domain.CustomerLoan;
import com.itexchange.demo.mybank.domain.Loan;
import com.itexchange.demo.mybank.util.Util;

public class CustomerLoanDAO extends BaseDAO {

	@Transactional
	public CustomerLoan createLoan(Customer customer, Loan loan, BigDecimal balance) {
		CustomerLoan customerLoan = CustomerLoan.builder()
				.balance(balance)
				.creationDate(Timestamp.valueOf(LocalDateTime.now()))
				.customer(customer)
				.loan(loan)
				.loanNumber(Util.generateLoanNumber(loan.getType()))
				.status("ACTIVE")
				.build();
		entityManager.persist(customerLoan);
		return customerLoan;
	}
	
	public List<CustomerLoan> getCustomerLoans(Customer customer) {
		String strQuery = "SELECT cl FROM CustomerLoan cl WHERE cl.customer = :customer";
		TypedQuery<CustomerLoan> query = entityManager.createQuery(strQuery, CustomerLoan.class);
		query.setParameter("customer", customer);
		return query.getResultList();
	}
}
