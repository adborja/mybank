package com.itexchange.demo.mybank.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.itexchange.demo.mybank.domain.Product;
import com.itexchange.demo.mybank.exception.ObjectNotFoundException;

@Component
public class ProductDAO extends BaseDAO {

	@Transactional
	public Product save(Product product) {
		entityManager.persist(product);
		return product;
	}
	
	public Product findByPrimaryKey(Integer id) {
		return entityManager.find(Product.class, id);
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

	public List<Product> findAll() {
		TypedQuery<Product> query = entityManager.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	public List<Product> findAllActive() {
		String sqlQuery = "SELECT * FROM product WHERE status = 'ACTIVE'";
		List<Object[]> result = entityManager.createNativeQuery(sqlQuery).getResultList();

		List<Product> products = new ArrayList<>();

		result.forEach(r -> {
			Integer id = (Integer) r[0];
			String name = (String) r[1];
			String description = (String) r[2];
			String status = (String) r[3];

			Product p = Product.builder().id(id).name(name).description(description).status(status).build();
			products.add(p);
		});

		return products;
	}
	
	public List<Product> findAllInactive() {
		String sqlQuery = "SELECT * FROM product WHERE status = 'INACTIVE'";
		List<Object[]> result = entityManager.createNativeQuery(sqlQuery).getResultList();
		
		List<Product> products = new ArrayList<>();
		
		result.forEach(r -> {
			Integer id = (Integer) r[0];
			String name = (String) r[1];
			String description = (String) r[2];
			String status = (String) r[3];
			
			Product p = Product.builder().id(id).name(name).description(description).status(status).build();
			products.add(p);
		});
		
		return products;
	}

}
