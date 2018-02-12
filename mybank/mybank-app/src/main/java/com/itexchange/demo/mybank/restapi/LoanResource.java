package com.itexchange.demo.mybank.restapi;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itexchange.demo.mybank.service.LoanService;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class LoanResource {

	private final LoanService loanService;

	public LoanResource(final LoanService loanService) {
		this.loanService = loanService;
	}

	@GetMapping("/loan/{customerId}/getActiveLoans")
	public ResponseEntity<List<Object>> getActiveLoansForCustomer(@PathVariable String customerId) {

		List<Object> list=loanService.findActiveLoand(customerId);

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
