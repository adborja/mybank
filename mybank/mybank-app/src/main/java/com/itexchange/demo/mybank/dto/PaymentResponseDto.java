package com.itexchange.demo.mybank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentResponseDto {

	
	private String status;
	private String transaction_number;
	private String date;
	private String customer_id;
	private String product_number;
	private String channel;
		
}
