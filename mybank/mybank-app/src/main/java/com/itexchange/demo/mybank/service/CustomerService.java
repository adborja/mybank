package com.itexchange.demo.mybank.service;

import org.springframework.stereotype.Service;

import com.itexchange.demo.mybank.dao.CustomerDAO;
import com.itexchange.demo.mybank.domain.Customer;
import com.itexchange.demo.mybank.dto.CustomerDto;
import com.itexchange.demo.mybank.exception.ObjectNotFoundException;

@Service
public class CustomerService {

	private CustomerDAO customerDAO;
	
	public CustomerService() {
		customerDAO = new CustomerDAO();
	}
	
	public void save(CustomerDto customer) {
		customerDAO.save(new Customer(0, customer.getName(), customer.getSurname(), customer.getCustomerId(),
				customer.getEmail(), customer.getMobile(), customer.getPhone(), customer.getPassword()));
	}
	
	public CustomerDto findByCustomerId(String customerId) throws ObjectNotFoundException {
		Customer customer = customerDAO.findByCustomerId(customerId);
		customer.setPassword("*****");
		return new CustomerDto(customer);
	}
	
	public boolean deleteCustomer(String customerId) throws ObjectNotFoundException {
		return customerDAO.delete(customerId);
	}

	public CustomerDto update(CustomerDto customer) {
		return customerDAO.update(customer);
	}
}
