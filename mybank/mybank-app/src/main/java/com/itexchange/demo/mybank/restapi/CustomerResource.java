package com.itexchange.demo.mybank.restapi;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itexchange.demo.mybank.dto.CustomerDto;
import com.itexchange.demo.mybank.dto.MessageDto;
import com.itexchange.demo.mybank.dto.ResponseDto;
import com.itexchange.demo.mybank.dto.StatusDto;
import com.itexchange.demo.mybank.exception.ObjectNotFoundException;
import com.itexchange.demo.mybank.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
class CustomerResource {

	private final CustomerService customerService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public CustomerResource(final CustomerService customerService, final BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.customerService = customerService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@GetMapping("/customer/{customerId}/get")
	public ResponseEntity<?> getCustomerByCustomerId(@PathVariable String customerId) {

		try {
			CustomerDto customer = customerService.findByCustomerId(customerId);
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		} catch (ObjectNotFoundException e) {
			MessageDto result = MessageDto.builder().statusCode(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
			return ResponseEntity.status(result.getStatusCode()).body(result);
		}
	}

	@PostMapping("/customer/sign-up")
	public ResponseDto signUp(@RequestBody CustomerDto customer) {
		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
		customerService.save(customer);

		ResponseDto response = ResponseDto.builder()
				.status(StatusDto.builder().statusCode(HttpStatus.OK.value()).statusDesc("SUCCESS").build())
				.response("Customer created succesfully").build();
		return response;
	}

	@DeleteMapping("/customer/delete")
	public ResponseDto delete(@RequestBody CustomerDto customer) {
		ResponseDto response;
		try {
			customerService.deleteCustomer(customer.getCustomerId());
			response = ResponseDto.builder()
					.status(StatusDto.builder().statusCode(HttpStatus.OK.value()).statusDesc("SUCCESS").build())
					.response("Customer deleted succesfully").build();
		} catch (ObjectNotFoundException e) {
			response = ResponseDto.builder()
					.status(StatusDto.builder().statusCode(HttpStatus.BAD_REQUEST.value()).statusDesc("ERROR").build())
					.response(e.getMessage()).build();

		}
		return response;
	}

	@PutMapping("/customer/update")
	public ResponseDto update(@RequestBody CustomerDto customer) {
		ResponseDto response;
		try {
			customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
			CustomerDto updated = customerService.update(customer);

			response = ResponseDto.builder()
					.status(StatusDto.builder().statusCode(HttpStatus.OK.value()).statusDesc("SUCCESS").build())
					.response("Customer updated succesfully").build();

		} catch (ObjectNotFoundException e) {
			response = ResponseDto.builder()
					.status(StatusDto.builder().statusCode(HttpStatus.BAD_REQUEST.value()).statusDesc("ERROR").build())
					.response(e.getMessage()).build();
		}
		return response;
	}
}
