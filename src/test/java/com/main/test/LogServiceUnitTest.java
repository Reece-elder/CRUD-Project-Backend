package com.main.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.main.persistence.domain.Log;
import com.main.persistence.domain.LogRepo;
import com.main.service.LogService;

@SpringBootTest
public class LogServiceUnitTest {

	private Log testLog = new Log("ship name example", "captain name example", "ship class example", "origin example",
			20);

	private Log testLog2 = new Log("ship name old", "captain name old", "ship class old", "origin old", 420);

	@Autowired
	private LogService service;

	@MockBean
	private LogRepo repo;

	// Given - When - Then
	@Test
	void testCreate() {

		// GIVEN
		Long id = 1l;
		Log newLog = testLog;
		Log savedLog = testLog;
		savedLog.setId(id);

		// WHEN
		Mockito.when(this.repo.save(newLog)).thenReturn(savedLog);

		// THEN
		assertThat(this.service.createNewLog(newLog)).isEqualTo(savedLog);
	}

	@Test
	void testUpdate() {

		// GIVEN
		Long id = 1l;
		Log newLog = testLog;
		Log oldLog = testLog2;
		oldLog.setId(id);
		Log updatedLog = testLog;
		updatedLog.setId(id);

		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(oldLog));
		Mockito.when(this.repo.save(updatedLog)).thenReturn(updatedLog);

		// THEN
		assertThat(this.service.updateLog(newLog, id)).isEqualTo(updatedLog);
	}
}
