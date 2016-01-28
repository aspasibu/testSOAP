package com.tsystems.logitest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tsystems.logitest.entity.DutyEvents;
import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.entity.enums.EventType;
import com.tsystems.logitest.repository.DutyRepository;
import com.tsystems.logitest.repository.DriverRepository;
import com.tsystems.logitest.service.DutyService;
import com.tsystems.logitest.service.errors.DutyErrorType;

public class DutyServiceImpl implements DutyService {

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private DutyRepository dutyEventsRepository;

	@Override
	public DutyErrorType logIn(String userName, String password) {
		return PerformAuthAction(userName, password, EventType.LOGIN);
	}

	@Override
	public DutyErrorType logOut(String userName) {
		return PerformAuthAction(userName, "", EventType.LOGOUT);
	}

	private DutyErrorType PerformAuthAction(String userName, String password, EventType type) {
		// verify if user is existed
		Driver driver = driverRepository.findByUserName(userName);
		if (driver == null) {
			return DutyErrorType.USER_NOT_FOUND;
		}

		// verify password
		if (type == EventType.LOGIN && password != null && !password.equals(driver.getPassword())) {
			return DutyErrorType.WRONG_PASSWORD;
		}

		// get last event - if is LOGIN then deny
		Pageable pages = new PageRequest(0, 1);
		List<DutyEvents> events = dutyEventsRepository.getEventsByDriver(driver, pages);
		if (events != null && events.size() != 0) {
			if (events.get(0).getType() == type) {
				if (type == EventType.LOGIN) {
					return DutyErrorType.SECOND_ATTEMPT_LOGIN;
				} else if (type == EventType.LOGOUT) {
					return DutyErrorType.SECOND_ATTEMPT_LOGOUT;
				}
			}
		}

		// if all Ok then insert into DB Login record
		saveAuthAction(driver, type);

		return DutyErrorType.SUCCESSFULL;
	}

	private boolean saveAuthAction(Driver driver, EventType type) {
		if (driver != null) {
			DutyEvents event = new DutyEvents();
			event.setDate(new Date());
			event.setDriver(driver);
			event.setType(type);
			return dutyEventsRepository.saveAndFlush(event) != null;
		} else {
			return false;
		}
	}

}
