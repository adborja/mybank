package com.itexchange.demo.mybank.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.itexchange.demo.mybank.domain.Transaction;

@Component
public class TransactionDAO extends BaseDAO {

	public Transaction findByTransactionNumber(Integer trxNumber) {
		String sqlQuery = "SELECT * FROM transaction WHERE transaction_number = ?";
		Query query = entityManager.createNativeQuery(sqlQuery, Transaction.class);
		query.setParameter(1, trxNumber);
		Transaction transaction = (Transaction) query.getSingleResult();
		return transaction;
	}

	public List<Transaction> getCustomerTransactions(String customerId) {
		String strQuery = "SELECT t FROM Transaction t "
				+ "JOIN t.customerProduct cp WHERE cp.customer.customerId = :custId";
		Query query = entityManager.createQuery(strQuery);
		query.setParameter("custId", customerId);
		List<Transaction> result = query.getResultList();
		return result;
	}
	
	
}
