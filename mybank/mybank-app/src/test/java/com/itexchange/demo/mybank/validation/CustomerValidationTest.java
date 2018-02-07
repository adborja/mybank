package com.itexchange.demo.mybank.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.itexchange.demo.mybank.domain.Customer;

public class CustomerValidationTest {

	private static Validator validator;

	static {
		Configuration<?> config = Validation.byDefaultProvider().configure();
		validator = config.buildValidatorFactory().getValidator();
	}

	@Test
	public void testCustomerValidation() {
		Customer customer = Customer.builder().build();

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer, GroupCustomerNames.class,
				GroupCustomerContactInfo.class);

		assertThat(violations).isNotEmpty();
		assertThat(violations.size()).isEqualTo(5);
	}

	@Test
	public void testCustomerEmailValidation() {
		final String badEmail = "bademail";

		Customer customer = Customer.builder()
				.name("Name")
				.surname("Surname")
				.customerId("customer_id")
				.email(badEmail)
				.mobile("123456789")
				.password("secret")
				.phone("987654").build();

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

		assertThat(violations).isNotEmpty();
		assertThat(violations.size()).isEqualTo(1);
		assertThat(violations.stream().findFirst().get().getPropertyPath().toString()).isEqualTo("email");
	}

	@Test
	public void testCustomerSequenceValidator() {
		final String badEmail = "bademail";

		Customer customer = Customer.builder()
				.name("Name")
				.surname("Surname")
				.customerId("customer_id")
				.email(badEmail)
				.mobile("123456789")
				.password("secret")
				.phone("987654")
				.build();

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer, GroupSequenceForUser.class);

		assertThat(violations).isNotEmpty();
		assertThat(violations.size()).isEqualTo(1);
		assertThat(violations.stream().findFirst().get().getPropertyPath().toString()).isEqualTo("email");
	}
}
