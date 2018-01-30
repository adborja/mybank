package com.itexchange.demo.mybank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "company_customer")
public class CompanyCustomer extends SpecialCustomer {

	@Column(name = "company_id")
	private String companyId;

	private String phone;
}
