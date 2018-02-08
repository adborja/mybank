package com.itexchange.demo.mybank.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.itexchange.demo.mybank.domain.Card;

public class CardDAO extends BaseDAO {

	@Transactional
	public Card save(Card card) {
		entityManager.persist(card);
		return card;
	}

	public List<Card> findAll() {
		return entityManager.createQuery("SELECT c FROM Card c", Card.class).getResultList();
	}

	public Card findByCardNumber(String number) {
		return entityManager.createQuery("SELECT c FROM Card c WHERE c.cardNumber = :number", Card.class)
				.setParameter("number", number)
				.getSingleResult();
	}
}
