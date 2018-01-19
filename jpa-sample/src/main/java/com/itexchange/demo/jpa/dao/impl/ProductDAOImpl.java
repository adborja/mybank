package com.itexchange.demo.jpa.dao.impl;

import javax.persistence.Query;

import com.itexchange.demo.jpa.dao.BaseDAO;
import com.itexchange.demo.jpa.dao.ProductDAO;
import com.itexchange.demo.jpa.domain.Product;
import com.itexchange.demo.jpa.exception.ObjectNotFoundException;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {

	@Override
	public Product findById(Integer id) throws ObjectNotFoundException {
		String strQuery = "SELECT p FROM " + getEntityName() + " p WHERE p.id = :id";
		Query query = em.createQuery(strQuery);
		query.setParameter("id", id);
		Product product = (Product) query.getSingleResult();
		return product;
	}

	@Override
	protected String getEntityName() {
		return Product.class.getName();
	}
}
