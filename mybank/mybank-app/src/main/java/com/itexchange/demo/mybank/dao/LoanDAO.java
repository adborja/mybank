package com.itexchange.demo.mybank.dao;

import org.springframework.stereotype.Component;

import com.itexchange.demo.mybank.domain.CarLoan;
import com.itexchange.demo.mybank.domain.HomeLoan;

@Component
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
}
