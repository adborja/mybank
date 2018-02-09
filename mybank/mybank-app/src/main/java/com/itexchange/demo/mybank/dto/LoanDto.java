package com.itexchange.demo.mybank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanDto {

	private Long id;
	private String type;
	private String fire_insurance_company;
	private String fire_insurance_id;
	private String status;

}
