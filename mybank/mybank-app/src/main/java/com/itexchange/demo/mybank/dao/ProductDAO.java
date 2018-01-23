package com.itexchange.demo.mybank.dao;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.itexchange.demo.mybank.domain.Product;
import com.itexchange.demo.mybank.exception.ObjectNotFoundException;

public class ProductDAO extends BaseDAO {

	@Transactional
	public Product save(Product product) {
		entityManager.merge(product);
		return product;
	}

	public Product findByName(String name) throws ObjectNotFoundException {
		String strQuery = "SELECT p FROM Product p WHERE p.name = :name";

		try {
			Product product = (Product) entityManager.createQuery(strQuery).setParameter("name", name)
					.getSingleResult();
			return product;
		} catch (NoResultException e) {
			throw new ObjectNotFoundException("Product not found with name: " + name);
		}
	}

	@Transactional
	public boolean delete(String name) throws ObjectNotFoundException {
		Product product = findByName(name);
		entityManager.remove(product);
		return true;
	}

	@Transactional
	public Product update(Product productoInfo) throws ObjectNotFoundException {
		Product product = findByName(productoInfo.getName());
		product.setDescription(productoInfo.getDescription());
		product.setName(productoInfo.getName());
		product.setStatus(productoInfo.getStatus());
		entityManager.merge(product);
		return productoInfo;
	}
}