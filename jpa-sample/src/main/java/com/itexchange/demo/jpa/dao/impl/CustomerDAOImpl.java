package com.itexchange.demo.jpa.dao.impl;

import javax.persistence.Query;

import com.itexchange.demo.jpa.dao.BaseDAO;
import com.itexchange.demo.jpa.dao.CustomerDAO;
import com.itexchange.demo.jpa.domain.Customer;
import com.itexchange.demo.jpa.exception.ObjectNotFoundException;

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO {

	@Override
	public Customer findByCustomerId(String customerId) throws ObjectNotFoundException {
		String strQuery = "SELECT c FROM " + getEntityName() + " c WHERE c.customerId = :customerId";
		Query query = em.createQuery(strQuery);
		query.setParameter("customerId", customerId);
		Customer customer = (Customer) query.getSingleResult();
		return customer;
	}

	@Override
	public Customer save(Customer customer) {
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		return customer;
	}

	@Override
	public Customer update(Customer customer) throws ObjectNotFoundException {
		return null;
	}

	@Override
	public void delete(Customer customer) throws ObjectNotFoundException {

	}
	
	@Override
	protected String getEntityName() {
		return Customer.class.getName();
	}
}
