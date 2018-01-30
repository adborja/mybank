package com.itexchange.demo.mybank.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.itexchange.demo.mybank.domain.Customer;
import com.itexchange.demo.mybank.domain.dto.CustomerNames;
import com.itexchange.demo.mybank.dto.CustomerDto;
import com.itexchange.demo.mybank.exception.ObjectNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerDAO extends BaseDAO {

	@Transactional
	public Customer save(Customer customer) {
		entityManager.merge(customer);
		return customer;
	}

	public CustomerNames findLowerFullName(String customerId) {
		String strQuery = "SELECT new com.itexchange.demo.mybank.domain.dto.CustomerNames(LOWER(c.name), LOWER(c.surname)) FROM Customer c WHERE c.customerId = :customerId";

		try {
			log.info(">>> Entity Manager" + entityManager);
			return (CustomerNames) entityManager.createQuery(strQuery).setParameter("customerId", customerId).getSingleResult();

		} catch (NoResultException e) {
			throw new ObjectNotFoundException("Customer not found with id: " + customerId);
		}
	}

	public Customer findByCustomerId(String customerId) throws ObjectNotFoundException {
		String strQuery = "SELECT c FROM Customer c WHERE c.customerId = :customerId";

		try {
			log.info(">>> Entity Manager" + entityManager);
			Customer customer = (Customer) entityManager.createQuery(strQuery).setParameter("customerId", customerId).getSingleResult();
			return customer;
		} catch (NoResultException e) {
			throw new ObjectNotFoundException("Customer not found with id: " + customerId);
		}
	}

	public List<Customer> findBySurname(String surname) {
		TypedQuery<Customer> query = entityManager.createNamedQuery("find_cust_by_surname", Customer.class).setParameter("surname", surname);
		return query.getResultList();
	}

	@Transactional
	public boolean delete(String customerId) throws ObjectNotFoundException {
		Customer customer = findByCustomerId(customerId);
		entityManager.remove(customer);
		return true;
	}

	@Transactional
	public CustomerDto update(CustomerDto customerInfo) throws ObjectNotFoundException {
		Customer customer = findByCustomerId(customerInfo.getCustomerId());
		customer.setEmail(customerInfo.getEmail());
		customer.setMobile(customerInfo.getMobile());
		customer.setPhone(customerInfo.getPhone());
		customer.setPassword(customerInfo.getPassword());
		entityManager.merge(customer);
		return customerInfo;
	}

	public List<CustomerNames> findCustomerNames() {
		return entityManager.createNamedQuery("find_cust_name_and_surname_dto").getResultList();
	}

	public List<Customer> findCustomersWithMoreThan(Long numberOfProducts) {
		String strQuery = "SELECT c FROM Customer c WHERE (SELECT count(cp) FROM CustomerProduct cp WHERE cp.customer = c) >= :numberOfProducts";
		Query query = entityManager.createQuery(strQuery);
		query.setParameter("numberOfProducts", numberOfProducts);
		List<Customer> customers = query.getResultList();
		return customers;
	}
}
