package com.itexchange.demo.mybank.dto;

import java.io.Serializable;

import com.itexchange.demo.mybank.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String surname;

	private String customerId;

	private String email;

	private String mobile;

	private String phone;

	private String password;
	
	public CustomerDto(Customer customer) {
		this.name = customer.getName();
		this.surname = customer.getSurname();
		this.customerId = customer.getCustomerId();
		this.email = customer.getEmail();
		this.mobile = customer.getMobile();
		this.phone = customer.getPhone();
		this.password = customer.getPassword();
	}
}
