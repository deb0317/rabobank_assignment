package com.raboBank.users.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RaboBankUserAccountApplicationTests {

	@Test
	void contextLoads() {
		int expectedValue = 10;
		int actualValue = 5 + 5;

		assertEquals(expectedValue, actualValue, "Sum of 5 + 5 should be 10");
	}
	}


