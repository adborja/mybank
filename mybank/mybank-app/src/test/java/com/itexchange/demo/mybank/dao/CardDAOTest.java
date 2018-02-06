package com.itexchange.demo.mybank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.Card;
import com.itexchange.demo.mybank.domain.CreditCard;
import com.itexchange.demo.mybank.domain.DebitCard;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardDAOTest {

	private static final String DEFAULT_CREDIT_CARD_NUMBER = "5000123499995555";

	private static final String DEFAULT_DEBIT_CARD_NUMBER = "6000777788880000";

	private static final String DEFAULT_CREDIT_CARD_CVV = "123";

	private static final Long ONE_YEAR_MILIS = 31536000000l;

	@Autowired
	private TestEntityManager testEntityManager;

	private CardDAO cardDAO;

	@Before
	public void before() {
		cardDAO = new CardDAO();
		cardDAO.setEntityManager(testEntityManager.getEntityManager());
	}

	@Test
	public void testFindAll() {
		// Creating a couple of cards
		createCreditCard("mastercard", "Mastercard credit card", "ACTIVE", new Timestamp(System.currentTimeMillis()),
				DEFAULT_CREDIT_CARD_NUMBER, "/images/" + DEFAULT_CREDIT_CARD_NUMBER + ".png",
				new Timestamp(System.currentTimeMillis() + ONE_YEAR_MILIS), DEFAULT_CREDIT_CARD_CVV);

		createDebitCard("maestro", "Maestro debit card", "ACTIVE", new Timestamp(System.currentTimeMillis()),
				DEFAULT_DEBIT_CARD_NUMBER, "/images/" + DEFAULT_DEBIT_CARD_NUMBER + ".png",
				new Timestamp(System.currentTimeMillis() + ONE_YEAR_MILIS), true);

		// Get card list
		List<Card> cards = cardDAO.findAll();

		// asserts
		assertThat(cards).isNotEmpty();
		assertThat(cards.size()).isEqualTo(2);
		assertThat(cards.get(0)).isInstanceOf(CreditCard.class);
		assertThat(cards.get(1)).isInstanceOf(DebitCard.class);
	}

	@Test
	public void testFindByCardNumber() {
		// Creating a couple of cards...
		// Creating a couple of cards
		createCreditCard("mastercard", "Mastercard credit card", "ACTIVE", new Timestamp(System.currentTimeMillis()),
				DEFAULT_CREDIT_CARD_NUMBER, "/images/" + DEFAULT_CREDIT_CARD_NUMBER + ".png",
				new Timestamp(System.currentTimeMillis() + ONE_YEAR_MILIS), DEFAULT_CREDIT_CARD_CVV);

		createDebitCard("maestro", "Maestro debit card", "ACTIVE", new Timestamp(System.currentTimeMillis()),
				DEFAULT_DEBIT_CARD_NUMBER, "/images/" + DEFAULT_DEBIT_CARD_NUMBER + ".png",
				new Timestamp(System.currentTimeMillis() + ONE_YEAR_MILIS), true);

		// Finding cards
		Card creditCard = cardDAO.findByCardNumber(DEFAULT_CREDIT_CARD_NUMBER);
		Card debitCard = cardDAO.findByCardNumber(DEFAULT_DEBIT_CARD_NUMBER);

		// asserts
		assertThat(creditCard).isInstanceOf(CreditCard.class);
		assertThat(debitCard).isInstanceOf(DebitCard.class);

		assertThat(((DebitCard) debitCard).isCivica()).isTrue();
		assertThat(((CreditCard) creditCard).getCvv()).isEqualTo(DEFAULT_CREDIT_CARD_CVV);
	}

	private CreditCard createCreditCard(String name, String description, String status, Timestamp createdDate,
			String cardNumber, String cardImageUrl, Timestamp dueDate, String cvv) {
		CreditCard creditCard = new CreditCard();
		creditCard.setCardImageUrl(cardImageUrl);
		creditCard.setCardNumber(cardNumber);
		creditCard.setCreatedDate(createdDate);
		creditCard.setCvv(cvv);
		creditCard.setDescription(description);
		creditCard.setDueDate(dueDate);
		creditCard.setName(name);
		creditCard.setStatus(status);

		cardDAO.save(creditCard);

		return creditCard;
	}

	private DebitCard createDebitCard(String name, String description, String status, Timestamp createdDate,
			String cardNumber, String cardImageUrl, Timestamp dueDate, boolean isCivica) {
		DebitCard debitCard = new DebitCard();
		debitCard.setCardImageUrl(cardImageUrl);
		debitCard.setCardNumber(cardNumber);
		debitCard.setCreatedDate(createdDate);
		debitCard.setCivica(isCivica);
		debitCard.setDescription(description);
		debitCard.setDueDate(dueDate);
		debitCard.setName(name);
		debitCard.setStatus(status);

		cardDAO.save(debitCard);

		return debitCard;
	}
}
