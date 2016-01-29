package org.aspasibu.logitest.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.aspasibu.logitest.LogiTestUtils;
import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.entity.DutyEvents;
import org.aspasibu.logitest.entity.enums.EventType;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.repository.DutyRepository;
import org.aspasibu.logitest.service.HosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class HosServiceImpl implements HosService {

	// private StringBuilder responseMeassge = new StringBuilder("%SURNAME,
	// %NAME for a period %START - %END: %TOTAL");

	private final String RESPONSE_USER_NOT_FOUND = "USER NOT FOUND";

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	@Autowired
	private DutyRepository dutyEventsRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public String calculate(String username, Date startPeriod, Date endPeriod) {		

		// check if driver with the username exists
		Driver driver = driverRepository.findByUserName(username);

		if (driver == null) {
			return RESPONSE_USER_NOT_FOUND;
		}

		//send message to JMS
		sendToJms(driver,startPeriod,endPeriod);
		// get list of activities for the driver

		List<DutyEvents> events = dutyEventsRepository.getEventsForPeriod(username, startPeriod, endPeriod);

		// we have to supplement the list of activities by first LOGIN entity
		// and last LOGOUT entity
		if (events != null && events.size() > 0) {
			events.add(0, new DutyEvents(driver, EventType.LOGIN, startPeriod));
			events.add(new DutyEvents(driver, EventType.LOGOUT, endPeriod));
		}

		long hours = getSumActivity(events);

		return LogiTestUtils.convertMillisToStrHours(hours);
	}

	/**
	 * Calculate sum of activity hours for a list of activities
	 * 
	 * @param events
	 * @return
	 */

	private long getSumActivity(List<DutyEvents> events) {
		if (events == null) {
			return 0;
		}

		long sum = 0;

		Date tmpDate = null;

		for (DutyEvents event : events) {
			if (event != null && event.getType() != null) {
				switch (event.getType()) {
				case LOGIN:
					tmpDate = event.getDate();
					break;
				case LOGOUT:
					if (tmpDate != null && event.getDate() != null) {
						sum += event.getDate().getTime() - tmpDate.getTime();
						tmpDate = null;
					}
					break;
				}
			}
		}

		return sum;
	}

	private void sendToJms(Driver driver, Date startPeriod, Date endPeriod) {
		StringBuilder text = new StringBuilder();
//		text.append("«апрос расчета активности дл€ водител€ ");
		text.append("Request for a calculation of a driver's activity ");
		text.append(driver.getSurname());
		text.append(", ");
		text.append(driver.getName());
		text.append(" during the period ");
		text.append(dateFormat.format(startPeriod)).append(" - ").append(dateFormat.format(endPeriod));
		jmsTemplate.convertAndSend(text.toString());
	}

}
