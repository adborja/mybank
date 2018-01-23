package com.itexchange.demo.mybank.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductDAOTest {

	private static final String DEFAULT_PRODUCT_NAME = "Charles Dickens";
	private static final String DEFAULT_PRODUCT_DESCRIPTION = "Good";
	private static final String DEFAULT_PRODUCT_STATUS = "on";

	@Autowired
	private TestEntityManager testEntityManager;

	private ProductDAO productDao;

	@Before
	public void before() {
		productDao = new ProductDAO();
		productDao.setEntityManager(testEntityManager.getEntityManager());
	}

	@Test
	public void testSave() {
		Product product = Product.builder().name(DEFAULT_PRODUCT_NAME).description(DEFAULT_PRODUCT_DESCRIPTION)
				.status(DEFAULT_PRODUCT_STATUS).build();

		productDao.save(product);

		Product found = productDao.findByName(DEFAULT_PRODUCT_NAME);
		assertThat(found.getName()).isEqualTo(DEFAULT_PRODUCT_NAME);
		assertNotNull(found.getId());
	}

	@Test
	public void testFindAll() {
		List<Product> products = productDao.findAll();

		assertThat(products).isNotEmpty();
		assertThat(products.get(0).getName().trim()).isEqualTo("Savings Account");
	}

	@Test
	public void testFindAllActive() {
		List<Product> products = productDao.findAllActive();
		assertThat(products).isNotEmpty();
		assertThat(products.get(0).getName().trim()).isEqualTo("Savings Account");
	}

}
