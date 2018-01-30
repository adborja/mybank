package com.itexchange.demo.mybank.dao;

import javax.transaction.Transactional;

import com.itexchange.demo.mybank.domain.Employee;

public class EmployeeDAO extends BaseDAO {

	@Transactional
	public Employee save(Employee employee) {
		entityManager.persist(employee);
		return employee;
	}

	public Employee findByEmployeeId(String employeeId) {
		return entityManager
				.createQuery("SELECT e FROM Employee e WHERE e.employeeId = :employeeId", Employee.class)
				.setParameter("employeeId", employeeId)
				.getSingleResult();
	}
}
