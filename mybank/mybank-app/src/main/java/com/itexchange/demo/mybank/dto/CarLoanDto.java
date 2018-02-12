package com.itexchange.demo.mybank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarLoanDto {

	private Integer id;
	private String type;
	private String life_insurance_company;
	private String life_insurance_id;
	private String status;

}
