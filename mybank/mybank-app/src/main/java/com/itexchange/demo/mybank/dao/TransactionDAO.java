package com.itexchange.demo.mybank.dao;

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
}
