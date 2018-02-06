package com.itexchange.demo.mybank.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
public class Channel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	private String name;
	
	private String description;
}