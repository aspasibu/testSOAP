package org.aspasibu.logitest.service.impl;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.service.DriverService;
import org.aspasibu.logitest.types.DriverResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;

	@Override
	public String addDriver(String surname, String name, String username, String password) {
		if (driverRepository.findByUserName(username) != null) {
			return DriverResponseType.ALREADY_CREATED.toString();
		}

		try {
			Driver driver = new Driver(surname, name, username, password);
			driver = driverRepository.saveAndFlush(driver);
			return driver.getId().toString();
		} catch (Exception e) {
			return DriverResponseType.DATABASE_EXCEPTION.toString();
		}

	}

	@Override
	public String deleteDriver(Long id) {
		try {
			driverRepository.delete(id);
			return DriverResponseType.SICCESSFULLY_DELETED.toString();
		} catch (Exception e) {
			return DriverResponseType.DATABASE_EXCEPTION.toString();
		}
	}

	@Override
	public String editDriver(Driver driver) {
		try {
			driverRepository.saveAndFlush(driver);
			return DriverResponseType.SICCESSFULLY_EDITED.toString();
		} catch (Exception e) {
			return DriverResponseType.DATABASE_EXCEPTION.toString();
		}
	}

}
