package com.tsystems.logitest.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.logitest.LogiTestUtils;
import com.tsystems.logitest.entity.AuthEvents;
import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.entity.enums.EventType;
import com.tsystems.logitest.repository.AuthRepository;
import com.tsystems.logitest.repository.DriverRepository;
import com.tsystems.logitest.service.HosService;

public class HosServiceImpl implements HosService {

	// private StringBuilder responseMeassge = new StringBuilder("%SURNAME,
	// %NAME for a period %START - %END: %TOTAL");

	private final String RESPONSE_USER_NOT_FOUND = "USER NOT FOUND";

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss");

	@Autowired
	private AuthRepository authEventsRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Override
	public String calculate(String username, Date startPeriod, Date endPeriod) {

		// check if driver with the username exists
		Driver driver = driverRepository.findByUserName(username);

		if (driver == null) {
			return RESPONSE_USER_NOT_FOUND;
		}

		// get list of activities for the driver
		List<AuthEvents> events = authEventsRepository
				.getEventsForPeriod(username, startPeriod, endPeriod);

		// we have to supplement the list of activities by first LOGIN entity and last LOGOUT entity
		if (events != null && events.size() > 0) {
			events.add(0, new AuthEvents(driver, EventType.LOGIN, startPeriod));
			events.add(new AuthEvents(driver, EventType.LOGOUT, endPeriod));
		}

		long hours = getSumActivity(events);

		return getResponseMessage(driver, startPeriod, endPeriod, hours);
	}

	/** builds a response message:
	 * "%SURNAME,%NAME for a period %START - %END: %TOTAL time"
	 * 
	 * @param driver
	 * @param startPeriod
	 * @param endPeriod
	 * @param total
	 * @return String message
	 */
	private String getResponseMessage(Driver driver, Date startPeriod,
			Date endPeriod, long total) {
		StringBuilder responseMeassge = new StringBuilder();
		responseMeassge.append(driver.getSurname());
		responseMeassge.append(", ");
		responseMeassge.append(driver.getName());
		responseMeassge.append(" for a period ");
		responseMeassge.append(dateFormat.format(startPeriod));
		responseMeassge.append(" - ");
		responseMeassge.append(dateFormat.format(endPeriod));
		responseMeassge.append(": ");
		responseMeassge.append(LogiTestUtils.convertMillisToHours(total));		
		return responseMeassge.toString();
	}

	/**Calculate sum of activity hours for a list of activities
	 * 
	 * @param events
	 * @return
	 */
	private long getSumActivity(List<AuthEvents> events) {
		if (events == null) {
			return 0;
		}

		long sum = 0;

		Date tmpDate = null;

		for (AuthEvents event : events) {
			if (event != null && event.getType() != null) {
				switch (event.getType()) {
					case LOGIN :
						tmpDate = event.getDate();
						break;
					case LOGOUT :
						if (tmpDate != null && event.getDate() != null) {
							sum += event.getDate().getTime()
									- tmpDate.getTime();
							tmpDate = null;
						}
						break;
				}
			}
		}

		return sum;
	}	

}
