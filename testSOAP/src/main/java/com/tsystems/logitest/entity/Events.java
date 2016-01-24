package com.tsystems.logitest.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tsystems.logitest.entity.enums.EventType;

@Entity
public class Events {
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;
	
	@Enumerated(EnumType.STRING)
	private EventType type;
	
	private Date date;
	
	public Events() {	
	}

}
