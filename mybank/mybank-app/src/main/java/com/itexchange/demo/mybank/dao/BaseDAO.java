package com.itexchange.demo.mybank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDAO {
	@PersistenceContext(unitName = "default")
	protected EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
