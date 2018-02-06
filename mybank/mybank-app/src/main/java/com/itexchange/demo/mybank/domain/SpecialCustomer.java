package com.itexchange.demo.mybank.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = { "id" })
@MappedSuperclass
@Data
public abstract class SpecialCustomer {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	protected Integer id;
	
	protected String name;
	
	protected String email;
	
	protected String password;
}
