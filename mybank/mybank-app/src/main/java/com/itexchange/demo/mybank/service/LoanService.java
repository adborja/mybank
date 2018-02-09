package com.itexchange.demo.mybank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itexchange.demo.mybank.dao.LoanDAO;
import com.itexchange.demo.mybank.domain.CustomerLoan;

@Service
public class LoanService {

	private LoanDAO loanDao;

	public LoanService() {
		loanDao = new LoanDAO();
	}

	public void findActiveLoand(String customerId) {

		List<CustomerLoan> customerLoan = loanDao.findActiveLoand(customerId);

		customerLoan.stream().forEach(c -> System.out.println(c.getId()));

	}

}
