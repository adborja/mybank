package com.itexchange.demo.mybank.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("digital")
public class DigitalBranch extends Branch {

	private static final long serialVersionUID = 1L;

	private String url;
}
