package com.itexchange.demo.mybank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeDAOTest {

	@Autowired
	private TestEntityManager testEntityManager;

	private EmployeeDAO employeeDAO;

	@Before
	public void before() {
		employeeDAO = new EmployeeDAO();
		employeeDAO.setEntityManager(testEntityManager.getEntityManager());
	}
	
	@Test
	public void testSave() {
		// Creating some employee
		Employee employee = Employee.builder()
				.address("some_address")
				.birthDate(new Timestamp(System.currentTimeMillis()))
				.designation("some_designation")
				.email("some@mybank.com")
				.employeeId("90199")
				.joiningDate(new Timestamp(System.currentTimeMillis()))
				.mobile("some_mobile")
				.name("name")
				.phone("some_phone")
				.salary(BigDecimal.valueOf(500))
				.surname("surname")
				.build();
		employeeDAO.save(employee);
		
		// Finding employee by employee id
		Employee found = employeeDAO.findByEmployeeId("90199");
		
		assertThat(found).isNotNull();
		assertThat(found.getDesignation()).isEqualTo("some_designation");
		assertThat(found.getEmail()).isEqualTo("some@mybank.com");
		assertThat(found.getPhone()).isEqualTo("some_phone");
		assertThat(found.getMobile()).isEqualTo("some_mobile");
	}
	
	@Test
	public void testFindByEmployeeId() {
		String employeeId = "90101";
		
		Employee employee = employeeDAO.findByEmployeeId(employeeId);
		
		assertThat(employee).isNotNull();
		assertThat(employee.getDesignation()).isEqualTo("bank teller");
		assertThat(employee.getEmail()).isEqualTo("rosemary.king@mybank.com");
		assertThat(employee.getPhone()).isEqualTo("+5743330099");
	}
}
