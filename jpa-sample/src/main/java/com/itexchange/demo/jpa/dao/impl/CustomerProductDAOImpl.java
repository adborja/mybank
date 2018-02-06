package com.itexchange.demo.jpa.dao.impl;

import com.itexchange.demo.jpa.dao.BaseDAO;
import com.itexchange.demo.jpa.dao.CustomerProductDAO;
import com.itexchange.demo.jpa.domain.CustomerProduct;

public class CustomerProductDAOImpl extends BaseDAO implements CustomerProductDAO {

	@Override
	public CustomerProduct save(CustomerProduct customerProduct) {
		em.getTransaction().begin();
		em.persist(customerProduct);
		em.getTransaction().commit();
		return customerProduct;
	}

	@Override
	protected String getEntityName() {
		return CustomerProduct.class.getName();
	}
}
