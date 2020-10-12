package com.main.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShipLog {

	@Id
	@GeneratedValue
	private Long id;
	private String shipName;
	private String captainName;
	private String shipClass;
	private String origin;
	private int cargo;

	// Constructors

	public ShipLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShipLog(String shipName, String captainName, String shipClass, String origin, int cargo) {
		super();
		// this.id = id;
		this.shipName = shipName;
		this.captainName = captainName;
		this.shipClass = shipClass;
		this.origin = origin;
		this.cargo = cargo;
	}

	// getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	public String getShipClass() {
		return shipClass;
	}

	public void setShipClass(String shipClass) {
		this.shipClass = shipClass;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
}
