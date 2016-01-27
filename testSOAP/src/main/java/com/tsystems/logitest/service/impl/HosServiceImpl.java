package com.tsystems.logitest.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.logitest.entity.AuthEvents;
import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.repository.AuthRepository;
import com.tsystems.logitest.repository.DriverRepository;
import com.tsystems.logitest.service.HosService;

public class HosServiceImpl implements HosService {

	// private StringBuilder responseMeassge = new StringBuilder("%SURNAME,
	// %NAME for a period %START - %END: %TOTAL");

	private final String RESPONSE_USER_NOT_FOUND = "USER NOT FOUND";

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	@Autowired
	private AuthRepository authEventsRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Override
	public String calculate(String username, Date startPeriod, Date endPeriod) {

		Driver driver = driverRepository.findByUserName(username);

		if (driverRepository.findByUserName(username) == null) {
			return RESPONSE_USER_NOT_FOUND;
		}

		List<AuthEvents> events = authEventsRepository.getEventsForPeriod(username, startPeriod, endPeriod);

		Date total = new Date(0);

		if (events != null || events.size() > 0) {

		}

		return getResponseMessage(driver, startPeriod, endPeriod, total.getTime());
	}

	private String getResponseMessage(Driver driver, Date startPeriod, Date endPeriod, long total) {
		StringBuilder responseMeassge = new StringBuilder();
		responseMeassge.append(driver.getSurname());
		responseMeassge.append(", ");
		responseMeassge.append(driver.getName());
		responseMeassge.append(" for a period ");
		responseMeassge.append(dateFormat.format(startPeriod));
		responseMeassge.append(" - ");
		responseMeassge.append(dateFormat.format(endPeriod));
		responseMeassge.append(": ");
		responseMeassge.append(String.valueOf(total));
		return responseMeassge.toString();
	}

}
