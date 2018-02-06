package com.itexchange.demo.jpa.dao;

import com.itexchange.demo.jpa.domain.Customer;
import com.itexchange.demo.jpa.exception.ObjectNotFoundException;

public interface CustomerDAO {

	Customer findByCustomerId(String customerId) throws ObjectNotFoundException;
	
	Customer save(Customer customer);
	
	Customer update(Customer customer) throws ObjectNotFoundException;
	
	void delete(Customer customer) throws ObjectNotFoundException;
}
