package com.main.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.persistence.domain.Log;
import com.main.service.LogService;

@RestController
@CrossOrigin
public class LogController {

	private LogService service;

	public LogController(LogService service) {
		super();
		this.service = service;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Log>> getAll() {
		return ResponseEntity.ok(this.service.getAll());
	}

	@PostMapping("/create")
	public ResponseEntity<Log> createNewLog(@RequestBody Log log) {
		return new ResponseEntity<Log>(this.service.createNewLog(log), HttpStatus.CREATED);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Object> removeById(@PathVariable Long id) {
		if (this.service.removeById(id)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Log> updateLog(@RequestBody Log log, @PathParam("id") Long id) {
		return new ResponseEntity<Log>(this.service.updateLog(log, id), HttpStatus.ACCEPTED);
	}

}
