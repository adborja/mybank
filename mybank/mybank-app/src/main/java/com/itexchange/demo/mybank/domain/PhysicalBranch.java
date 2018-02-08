package com.itexchange.demo.mybank.domain;

import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("physical")
public class PhysicalBranch extends Branch {

	private static final long serialVersionUID = 1L;

	private String address;

	private String phone;
	
	private LocalTime opensAt;

	private LocalTime closesAt;
}