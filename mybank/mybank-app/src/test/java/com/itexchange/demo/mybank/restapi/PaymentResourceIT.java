package com.itexchange.demo.mybank.restapi;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Sql(executionPhase = AFTER_TEST_METHOD, statements = "DROP ALL OBJECTS DELETE FILES")
public class PaymentResourceIT {

	private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.JSON;

	private static final String PAYMENT_PAY_URL = "/payment/pay";

	@LocalServerPort
	private int port;

	@Before
	public void setup() {
		RestAssured.port = port;
		RestAssured.basePath = "/api/1.0";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Test
	public void testSimplePOSPurchase() {
		final String json = "{\r\n" + 
				"	\"customer_id\": \"3012345\",\r\n" + 
				"	\"product_number\": \"1000000003\",\r\n" + 
				"	\"channel\": \"POS\",\r\n" + 
				"	\"amount\": 10000,\r\n" + 
				"	\"date\": \"2018-02-08 10:00:00\",\r\n" + 
				"	\"type\": 3\r\n" + 
				"}";

		/*
		 Expected response:
		 -----------------------
		 {
			"status": "APPROVED",
			"transaction_number": "0000",
			"date": "2018-02-08 10:00:00",
			"customer_id": "3012345",
			"product_number": "1000000003",
			"channel": "POS"
		}
		 -----------------------
		*/
		String result = given()
							.contentType(DEFAULT_CONTENT_TYPE)
							.body(json)
						.when()
							.post(PAYMENT_PAY_URL)
						.then()
							.assertThat()
								.statusCode(200)
						.extract().body().asString();
		
		assertThat(result).contains("\"status\": \"APPROVED\"");
		assertThat(result).contains("transaction_number");
		assertThat(result).contains("\"customer_id\": \"3012345\"");
		assertThat(result).contains("\"product_number\": \"1000000003\"");
		assertThat(result).contains("\"channel\": \"POS\"");
		assertThat(result).contains("\"date\"");
	}
	
	@Test
	public void testATMWithdrawal() {
		final String json = "{\r\n" + 
				"	\"customer_id\": \"3012345\",\r\n" + 
				"	\"product_number\": \"1000000003\",\r\n" + 
				"	\"channel\": \"ATM\",\r\n" + 
				"	\"amount\": 5000,\r\n" + 
				"	\"date\": \"2018-02-08 11:00:00\",\r\n" + 
				"	\"type\": 1\r\n" + 
				"}";
		
		/*
		Expected response:
		-----------------------
		{
			"status": "APPROVED",
			"transaction_number": "0000",
			"date": "2018-02-08 10:00:00",
			"customer_id": "3012345",
			"product_number": "1000000003",
			"channel": "ATM"
		}
		 -----------------------
		*/
		String result = given()
				.contentType(DEFAULT_CONTENT_TYPE)
				.body(json)
			.when()
				.post(PAYMENT_PAY_URL)
			.then()
				.assertThat()
					.statusCode(200)
			.extract().body().asString();

		assertThat(result).contains("\"status\": \"APPROVED\"");
		assertThat(result).contains("transaction_number");
		assertThat(result).contains("\"customer_id\": \"3012345\"");
		assertThat(result).contains("\"product_number\": \"1000000003\"");
		assertThat(result).contains("\"channel\": \"ATM\"");
		assertThat(result).contains("\"date\"");
	}
}