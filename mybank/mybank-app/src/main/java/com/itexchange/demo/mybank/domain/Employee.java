package com.itexchange.demo.mybank.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(of = { "employeeId" })
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SecondaryTable(
		name = "employee_details", 
		pkJoinColumns = @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
)
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(name = "employee_id")
	private String employeeId;

	private String designation;
	
	//Details
	@Column(table = "employee_details")
	private String name;

	@Column(table = "employee_details")
	private String surname;

	@Column(table = "employee_details")
	private String email;

	@Column(table = "employee_details")
	private String phone;

	@Column(table = "employee_details")
	private String mobile;

	@Column(table = "employee_details")
	private String address;

	@Column(name = "birth_date", table = "employee_details")
	private Timestamp birthDate;
	
	@Column(name = "joining_date", table = "employee_details")
	private Timestamp joiningDate;

	@Column(table = "employee_details")
	private BigDecimal salary;
}
