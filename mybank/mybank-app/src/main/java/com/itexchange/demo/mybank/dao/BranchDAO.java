package com.itexchange.demo.mybank.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.itexchange.demo.mybank.domain.Branch;
import com.itexchange.demo.mybank.domain.DigitalBranch;
import com.itexchange.demo.mybank.domain.PhysicalBranch;

public class BranchDAO extends BaseDAO {

	@Transactional
	public Branch save(Branch branch) {
		entityManager.persist(branch);
		return branch;
	}

	public Branch findByName(final String name) {
		String strQuery = "SELECT b FROM Branch b WHERE b.name = :name";
		return entityManager.createQuery(strQuery, Branch.class).setParameter("name", name).getSingleResult();
	}

	public List<Branch> findAll() {
		return entityManager.createQuery("SELECT b FROM Branch b", Branch.class).getResultList();
	}

	public List<DigitalBranch> findAllDigitalBranches() {
		return entityManager.createQuery("SELECT db FROM DigitalBranch db", DigitalBranch.class).getResultList();
	}

	public List<PhysicalBranch> findAllPhysicalBranches() {
		return entityManager.createQuery("SELECT fb FROM PhysicalBranch fb", PhysicalBranch.class).getResultList();
	}
}
