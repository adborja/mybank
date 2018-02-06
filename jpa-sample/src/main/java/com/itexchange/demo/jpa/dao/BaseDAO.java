package com.itexchange.demo.jpa.dao;

import javax.persistence.EntityManager;

import com.itexchange.demo.jpa.util.JPAUtil;

public abstract class BaseDAO {
	protected EntityManager em;

	public BaseDAO() {
		em = JPAUtil.getEntityManagerFactory().createEntityManager();
	}
	
	protected abstract String getEntityName();
}
