package com.itexchange.demo.mybank.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import com.itexchange.demo.mybank.domain.dto.CustomerNames;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@NamedNativeQuery(
	name = "find_cust_name_and_surname_dto",
	query = "SELECT name, surname FROM customer",
	resultSetMapping = "name_and_surname_dto"
)
@SqlResultSetMapping(
	name = "name_and_surname_dto",
	classes = @ConstructorResult(
		targetClass = CustomerNames.class,
		columns = {
			@ColumnResult(name = "name"),
			@ColumnResult(name = "surname")
		}
	)
)
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String name;

	private String surname;

	@Column(name = "customer_id")
	private String customerId;

	private String email;

	private String mobile;

	private String phone;

	private String password;
}