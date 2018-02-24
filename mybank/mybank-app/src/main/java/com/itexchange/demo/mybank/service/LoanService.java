package com.itexchange.demo.mybank.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.itexchange.demo.mybank.dao.LoanDAO;
import com.itexchange.demo.mybank.domain.CarLoan;
import com.itexchange.demo.mybank.domain.CustomerLoan;
import com.itexchange.demo.mybank.domain.HomeLoan;
import com.itexchange.demo.mybank.dto.CarLoanDto;
import com.itexchange.demo.mybank.dto.HomeLoanDto;

@Service
public class LoanService {

	private LoanDAO loanDao;

	public LoanService() {
		loanDao = new LoanDAO();
	}

	public List<Object> findActiveLoand(String customerId) {

		List<CustomerLoan> customerLoan = loanDao.findActiveLoand(customerId);

		return customerLoan.stream()
		            .map(customerToObject)
		            .collect(Collectors.<Object> toList());

	}


	Function<CustomerLoan, Object> customerToObject = new Function<CustomerLoan, Object>() {

		public Object apply(CustomerLoan t) {
			if (t.getLoan() instanceof HomeLoan) {

				return  HomeLoanDto.builder()
						.id(((HomeLoan) t.getLoan()).getId())
						.type(t.getLoan().getType().toString())
						.fire_insurance_company(((HomeLoan) t.getLoan()).getFireInsuranceCompany())
						.fire_insurance_id(((HomeLoan) t.getLoan()).getFireInsuranceId())
						.status( t.getStatus())
						.build();
						
						
			} else {
				return  CarLoanDto.builder()
						.id(((CarLoan) t.getLoan()).getId())
						.type(t.getLoan().getType().toString())
						.life_insurance_company(((CarLoan) t.getLoan()).getLifeInsuranceCompany())
						.life_insurance_id(((CarLoan) t.getLoan()).getLifeInsuranceId())
						.status( t.getStatus())
						.build();
			}

		}
	};

}
