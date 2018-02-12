package com.itexchange.demo.mybank.restapi;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itexchange.demo.mybank.dto.PaymentDto;
import com.itexchange.demo.mybank.dto.PaymentResponseDto;
import com.itexchange.demo.mybank.service.PaymentService;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class PaymentResource {

	private final PaymentService paymentService;

	public PaymentResource(final PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping("/payment/pay")
	public PaymentResponseDto purchase(@RequestBody PaymentDto payment) {

		return PaymentResponseDto.builder()
				.channel("POS")
				.status("APPROVED")
				.transaction_number("0000")
				.date("2018-02-08 10:00:00")
				.customer_id("3012345")
				.product_number("1000000003")
				.channel("POS")
				.build();
		
	}
	
}
