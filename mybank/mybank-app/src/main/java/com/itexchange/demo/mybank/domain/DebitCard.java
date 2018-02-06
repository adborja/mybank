package com.itexchange.demo.mybank.domain;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class DebitCard extends Card {
	
	private static final long serialVersionUID = 1L;
	
	private boolean civica;
}
