package com.itexchange.demo.mybank.dao;

import java.util.List;

import com.itexchange.demo.mybank.domain.CarLoan;
import com.itexchange.demo.mybank.domain.CustomerLoan;
import com.itexchange.demo.mybank.domain.HomeLoan;

public class LoanDAO extends BaseDAO {
	
	public CarLoan findCarLoanByName(final String name) {
		return entityManager
				.createQuery("SELECT cl FROM CarLoan cl WHERE cl.name = :name", CarLoan.class)
				.setParameter("name", name)
				.getSingleResult();
	}
	
	public HomeLoan findHomeLoanByName(final String name) {
		return entityManager
				.createQuery("SELECT hl FROM HomeLoan hl WHERE hl.name = :name", HomeLoan.class)
				.setParameter("name", name)
				.getSingleResult();
	}

	public List<CustomerLoan> findActiveLoand(String customerId) {
		
		return entityManager
				.createQuery("SELECT cl FROM CustomerLoan cl WHERE cl.customer.customerId = :customerId AND cl.loan.status = 'ACTIVE'",CustomerLoan.class)
				.setParameter("customerId", customerId)
				.getResultList();
		
	}
}
