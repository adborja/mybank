package com.itexchange.demo.mybank.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductDAOTest {

	@Autowired
	private TestEntityManager testEntityManager;

	private ProductDAO productDAO;

	@Before
	public void before() {
		productDAO = new ProductDAO();
		productDAO.setEntityManager(testEntityManager.getEntityManager());
	}
	
	@Test
	public void testFindAll() {
		List<Product> products = productDAO.findAll();
		
		assertThat(products).isNotEmpty();
		assertThat(products.get(0).getName().trim()).isEqualTo("Savings Account");
	}
	
	@Test
	public void testFindAllActive() {
		List<Product> products = productDAO.findAllActive();
		assertThat(products).isNotEmpty();
		assertThat(products.get(0).getName().trim()).isEqualTo("Savings Account");
	}
}
