package com.itexchange.demo.mybank.util;

import java.time.LocalDateTime;
import java.util.Random;

import com.itexchange.demo.mybank.domain.LoanType;

public final class Util {

	public static String generateLoanNumber(LoanType type) {
		String base = type == LoanType.CAR ? "1100" : "2100";
		String day = String.format("%02d", LocalDateTime.now().getDayOfMonth());
		Random random = new Random();
		String randomNumber = String.format("%04d", random.nextInt(10000));
		return base + day + randomNumber;
	}
}
