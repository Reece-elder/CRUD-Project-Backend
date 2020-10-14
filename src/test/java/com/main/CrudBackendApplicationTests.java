package com.main;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
class CrudBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void giveCoveragePlz() {
		EqualsVerifier.simple();
	}

}
