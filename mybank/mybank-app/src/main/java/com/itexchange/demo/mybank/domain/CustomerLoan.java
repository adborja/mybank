package com.itexchange.demo.mybank.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer_loan")
public class CustomerLoan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loan_id")
	private Loan loan;

	@Column(name = "creation_date")
	private Timestamp creationDate;

	@Column(name = "termination_date")
	private Timestamp terminationDate;

	private BigDecimal balance;

	private String status;

	@Column(name = "loan_number")
	private String loanNumber;
}
