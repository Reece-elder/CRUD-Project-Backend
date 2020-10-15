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

	public List<Log> getAllSortShipNameAsc() {
		return this.repo.findByOrderByShipNameAsc();
	}

	public List<Log> getAllSortShipNameDesc() {
		return this.repo.findByOrderByShipNameDesc();
	}

	public List<Log> getAllSortCaptainNameAsc() {
		return this.repo.findByOrderByCaptainNameAsc();
	}

	public List<Log> getAllSortCaptainNameDesc() {
		return this.repo.findByOrderByCaptainNameDesc();
	}

	public List<Log> getAllSortShipClassAsc() {
		return this.repo.findByOrderByShipClassAsc();
	}

	public List<Log> getAllSortShipClassDesc() {
		return this.repo.findByOrderByShipClassDesc();
	}

	public List<Log> getAllSortOriginAsc() {
		return this.repo.findByOrderByOriginAsc();
	}

	public List<Log> getAllSortOriginDesc() {
		return this.repo.findByOrderByOriginDesc();
	}

	public List<Log> getAllSortCargoAsc() {
		return this.repo.findByOrderByCargoAsc();
	}

	public List<Log> getAllSortCargoDesc() {
		return this.repo.findByOrderByCargoDesc();
	}

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
