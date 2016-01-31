package org.aspasibu.logitest.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author aspasibu
 *
 *         Class responsible for sending jms messages
 */
public class JmsSender {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private DriverRepository driverRepository;

	public JmsSender() {
	}

	public void sendToJms(String username, Date startPeriod, Date endPeriod) {
		// check if driver with the username exists
		Driver driver = driverRepository.findByUserName(username);

		StringBuilder text = new StringBuilder();
		text.append("Request for a calculation of a driver's activity ");
		if (driver == null) {
			text.append(username);
		} else {
			text.append(driver.getSurname());
			text.append(", ");
			text.append(driver.getName());
		}
		text.append(" during the period ");
		text.append(dateFormat.format(startPeriod)).append(" - ").append(dateFormat.format(endPeriod));
		try {
			jmsTemplate.convertAndSend(text.toString());
		} catch (JmsException ex) {
			// TODO add logging
			System.out.println(ex.getMessage());
		}
	}

}
