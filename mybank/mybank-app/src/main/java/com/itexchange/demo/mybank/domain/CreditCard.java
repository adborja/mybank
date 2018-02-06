package com.itexchange.demo.mybank.domain;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class CreditCard extends Card {

	private static final long serialVersionUID = 860441312448867545L;

	private String cvv;
}
