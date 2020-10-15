package com.main.service;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.main.exceptions.LogNotFoundException;
import com.main.persistence.domain.Log;
import com.main.persistence.domain.LogRepo;

@Service
public class LogService {

	private LogRepo repo;

	public LogService(LogRepo repo) {
		super();
		this.repo = repo;
	}

	public List<Log> getAll() {
		return this.repo.findAll();
	}

//	public List<Log>

	public Log createNewLog(@RequestBody Log log) {
		return this.repo.save(log);
	}

	public boolean removeById(@PathVariable Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public Log updateLog(@RequestBody Log log, @PathParam("id") Long id) {
		Log oldLog = this.repo.findById(id).orElseThrow(() -> new LogNotFoundException());

		oldLog.setCaptainName(log.getCaptainName());
		oldLog.setShipName(log.getShipName());
		oldLog.setCargo(log.getCargo());
		oldLog.setOrigin(log.getOrigin());
		oldLog.setShipClass(log.getShipClass());

		return this.repo.save(oldLog);
	}

}
