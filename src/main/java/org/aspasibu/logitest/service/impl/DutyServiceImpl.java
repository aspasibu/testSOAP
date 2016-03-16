package org.aspasibu.logitest.service.impl;

import java.util.Date;
import java.util.List;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.entity.DutyEvents;
import org.aspasibu.logitest.entity.enums.EventType;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.repository.DutyRepository;
import org.aspasibu.logitest.service.DutyService;
import org.aspasibu.logitest.types.DutyResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DutyServiceImpl implements DutyService {

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private DutyRepository dutyEventsRepository;

	@Override
	public DutyResponseType logIn(String userName, String password) {
		return PerformAuthAction(userName, password, EventType.LOGIN);
	}

	@Override
	public DutyResponseType logOut(String userName) {
		return PerformAuthAction(userName, "", EventType.LOGOUT);
	}

	private DutyResponseType PerformAuthAction(String userName, String password, EventType type) {
		// verify if user is existed
		Driver driver = driverRepository.findByUserName(userName);
		if (driver == null) {
			return DutyResponseType.USER_NOT_FOUND;
		}

		// verify password
		if (type == EventType.LOGIN && !driver.passwordEquals(password)) {
			return DutyResponseType.WRONG_PASSWORD;
		}

		// get last event - if is LOGIN then deny
		Pageable pages = new PageRequest(0, 1);
		List<DutyEvents> events = dutyEventsRepository.getEventsByDriver(driver, pages);
		if (events != null && events.size() != 0) {
			if (events.get(0).getType() == type) {
				if (type == EventType.LOGIN) {
					return DutyResponseType.SECOND_ATTEMPT_LOGIN;
				} else if (type == EventType.LOGOUT) {
					return DutyResponseType.SECOND_ATTEMPT_LOGOUT;
				}
			}
		}

		// if all Ok then insert into DB Login record
		saveAuthAction(driver, type);

		return DutyResponseType.SUCCESSFULL;
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
