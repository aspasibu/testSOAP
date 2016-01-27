package com.tsystems.logitest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tsystems.logitest.entity.AuthEvents;
import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.entity.enums.EventType;
import com.tsystems.logitest.repository.AuthRepository;
import com.tsystems.logitest.repository.DriverRepository;
import com.tsystems.logitest.service.AuthService;
import com.tsystems.logitest.service.errors.AuthErrorType;

public class AuthServiceImpl implements AuthService {

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private AuthRepository authEventsRepository;

	@Override
	public AuthErrorType logIn(String userName, String password) {
		return PerformAuthAction(userName, password, EventType.LOGIN);
	}

	@Override
	public AuthErrorType logOut(String userName) {
		return PerformAuthAction(userName, "", EventType.LOGOUT);
	}

	private AuthErrorType PerformAuthAction(String userName, String password, EventType type) {
		// verify if user is existed
		Driver driver = driverRepository.findByUserName(userName);
		if (driver == null) {
			return AuthErrorType.USER_NOT_FOUND;
		}

		// verify password
		if (type == EventType.LOGIN && password != null && !password.equals(driver.getPassword())) {
			return AuthErrorType.WRONG_PASSWORD;
		}

		// get last event - if is LOGIN then deny
		Pageable pages = new PageRequest(0, 1);
		List<AuthEvents> events = authEventsRepository.getEventsByDriver(driver, pages);
		if (events != null && events.size() != 0) {
			if (events.get(0).getType() == type) {
				if (type == EventType.LOGIN) {
					return AuthErrorType.SECOND_ATTEMPT_LOGIN;
				} else if (type == EventType.LOGOUT) {
					return AuthErrorType.SECOND_ATTEMPT_LOGOUT;
				}
			}
		}

		// if all Ok then insert into DB Login record
		saveAuthAction(driver, type);

		return AuthErrorType.SUCCESSFULL;
	}

	private boolean saveAuthAction(Driver driver, EventType type) {
		if (driver != null) {
			AuthEvents event = new AuthEvents();
			event.setDate(new Date());
			event.setDriver(driver);
			event.setType(type);
			return authEventsRepository.saveAndFlush(event) != null;
		} else {
			return false;
		}
	}

}
