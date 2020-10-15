package com.main.persistence.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<Log, Long> {

	List<Log> findByOrderByShipNameAsc();

	List<Log> findByOrderByShipNameDesc();

	List<Log> findByOrderByCaptainNameAsc();

	List<Log> findByOrderByCaptainNameDesc();

	List<Log> findByOrderByShipClassAsc();

	List<Log> findByOrderByShipClassDesc();

	List<Log> findByOrderByOriginAsc();

	List<Log> findByOrderByOriginDesc();

	List<Log> findByOrderByCargoAsc();

	List<Log> findByOrderByCargoDesc();

}
