package com.itexchange.demo.mybank.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.itexchange.demo.mybank.domain.Product;

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
}
