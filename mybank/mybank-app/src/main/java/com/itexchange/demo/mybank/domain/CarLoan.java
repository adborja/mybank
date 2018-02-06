package com.itexchange.demo.mybank.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, of = { "id" })
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarLoan extends Loan {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	private String lifeInsuranceCompany;
	
	private String lifeInsuranceId;
	
	@Override
	public LoanType getType() {
		return LoanType.CAR;
	}
}