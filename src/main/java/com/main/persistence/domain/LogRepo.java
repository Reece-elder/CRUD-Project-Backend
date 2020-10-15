package com.main.persistence.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<Log, Long> {

	List<Log> findByOrderByShipNameAsc();

}
