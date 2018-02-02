package com.itexchange.demo.mybank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "senior_customer")
public class SeniorCustomer extends SpecialCustomer {

	private Integer age;

	@Column(name = "billing_address")
	private String billingAddress;

}
