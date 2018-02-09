package com.itexchange.demo.mybank.dao;

import java.util.List;

import javax.persistence.PostPersist;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.itexchange.demo.mybank.domain.Transaction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionDAO extends BaseDAO {

	@PostPersist
	public void onPostPersist() {
		log.info("New transaction has been created");
	}

	@Transactional
	public void persist(Transaction transaction) {
		entityManager.persist(transaction);
	}

	public Transaction findByTransactionNumber(Integer trxNumber) {
		String sqlQuery = "SELECT * FROM transaction WHERE transaction_number = ?";
		Query query = entityManager.createNativeQuery(sqlQuery, Transaction.class);
		query.setParameter(1, trxNumber);
		Transaction transaction = (Transaction) query.getSingleResult();
		return transaction;
	}

	public Transaction findCountTransactions(Integer customerId) {
		String sqlQuery = "SELECT COUNT(1) FROM transaction JOIN t.customerProduct cp WHERE cp.customer.customerId = :custId";
		Query query = entityManager.createNativeQuery(sqlQuery, Transaction.class);
		query.setParameter(1, customerId);
		Transaction transaction = (Transaction) query.getSingleResult();
		return transaction;
	}

	public List<Transaction> getCustomerTransactions(String customerId) {
		String strQuery = "SELECT t FROM Transaction t " + "JOIN t.customerProduct cp WHERE cp.customer.customerId = :custId";
		Query query = entityManager.createQuery(strQuery);
		query.setParameter("custId", customerId);
		List<Transaction> result = query.getResultList();
		return result;
	}

}
