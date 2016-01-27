package com.tsystems.logitest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.logitest.entity.AuthEvents;
import com.tsystems.logitest.repository.AuthRepository;
import com.tsystems.logitest.repository.DriverRepository;
import com.tsystems.logitest.service.HosService;

public class HosServiceImpl implements HosService {

	private String responseMeassge = "%SURNAME, %NAME for a period %START - %END";

	@Autowired
	private AuthRepository authEventsRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Override
	public String calculate(String username, Date startPeriod, Date endPeriod) {

		List<AuthEvents> events = authEventsRepository.getEventsForPeriod(username, startPeriod, endPeriod);

		if (events == null || events.size() == 0) {
			return null;
		}

		return null;
	}

}
