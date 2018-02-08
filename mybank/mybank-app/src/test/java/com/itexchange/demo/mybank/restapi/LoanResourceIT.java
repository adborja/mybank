package com.itexchange.demo.mybank.restapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

import java.util.HashMap;
import java.util.Map;

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

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Sql(executionPhase = AFTER_TEST_METHOD, statements = "DROP ALL OBJECTS DELETE FILES")
public class LoanResourceIT {
	private static final String ACTIVE_LOAN_URL = "/loan/{customerId}/getActiveLoans";

	@LocalServerPort
	private int port;
	
	@Before
	public void setup() {
		RestAssured.port = port;
		RestAssured.basePath = "/api/1.0";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
	
	@Test
	public void testGetActiveLoansForCustomer() {
		
		final Map<String, String> params = new HashMap<>();
		params.put("customerId", "3012345");
		
		/**
		 Expected response:
		 ----------------------
		 [
			{
				"type": "HOME",
				"id": 0,
				"fire_insurance_company": "company",
				"fire_insurance_id": "0",
				"status": "ACTIVE"
			},
			{
				"type": "CAR",
				"id": 0,
				"life_insurance_company": "company",
				"life_insurance_id": "0",
				"status": "ACTIVE"
			}
		]
		-------------------------
		*/
		
		given()
		.when()
			.get(ACTIVE_LOAN_URL, params)
		.then()
			.assertThat()
				.statusCode(200)
				.body("size()", is(3))
				.body("type", hasItems("HOME", "CAR"));
	}
}
