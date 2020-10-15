package com.main.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.persistence.domain.Log;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:log-schema.sql",
		"classpath:log-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class LogIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	private Log testLog = new Log("ship name example", "captain name example", "ship class example", "origin example",
			20);

	@Test
	void testCreate() throws Exception {

		// Converting newLog to a JSON string
		String requestBody = this.mapper.writeValueAsString(testLog);

		// Creating a RequestBuilder object called 'Request'
		// Giving request a method 'POST', content type 'JSON' and the actual content,
		// requestBody created earlier
		RequestBuilder request = post("/create").contentType(MediaType.APPLICATION_JSON).content(requestBody);

		// ???
		ResultMatcher checkStatus = status().isCreated();

		// Test log object to compare against the original newLog, with the same values
		Log savedLog = testLog;

		// Setting the ID to 1 (which should be the same as the original log)
		savedLog.setId(2l);

		// Converting savedLog to a JSON string to compare with the request body
		String resultBody = this.mapper.writeValueAsString(savedLog);

		// ???
		ResultMatcher checkBody = content().json(resultBody);

		// Testing the request body compared to the response body
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/remove/1");

		ResultMatcher checkStatus = status().isOk();

		this.mockMVC.perform(request).andExpect(checkStatus);
	}

	@Test
	void testRead() throws Exception {

		testLog.setId(1l);
		List<Log> logArray = new ArrayList<>();
		logArray.add(testLog);
		String responseBody = this.mapper.writeValueAsString(logArray);

		RequestBuilder request = get("/getAll");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(responseBody);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testSortData() throws Exception {
		sorting("/getAll/sort/shipAsc");
		sorting("/getAll/sort/shipDesc");
		sorting("/getAll/sort/captainAsc");
		sorting("/getAll/sort/captainDesc");
		sorting("/getAll/sort/classAsc");
		sorting("/getAll/sort/classDesc");
		sorting("/getAll/sort/originAsc");
		sorting("/getAll/sort/originDesc");
		sorting("/getAll/sort/cargoAsc");
		sorting("/getAll/sort/cargoDesc");
	}

	void sorting(String url) throws Exception {

		testLog.setId(1l);
		List<Log> logArray = new ArrayList<>();
		logArray.add(testLog);
		String responseBody = this.mapper.writeValueAsString(logArray);

		RequestBuilder request = get(url);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(responseBody);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {

		String requestBody = this.mapper.writeValueAsString(testLog);
		RequestBuilder request = put("/update?id=1").contentType(MediaType.APPLICATION_JSON).content(requestBody);

		Log resultLog = testLog;
		resultLog.setId(1l);

		String resultsBody = this.mapper.writeValueAsString(resultLog);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(resultsBody);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody).andReturn();

	}

}
