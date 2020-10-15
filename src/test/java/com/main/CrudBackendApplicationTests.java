package com.main;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.main.persistence.domain.Log;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
class CrudBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.forClass(Log.class).usingGetClass().verify();
	}

}
