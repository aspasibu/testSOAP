package org.aspasibu.logitest.service.impl;

import java.util.List;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.service.DriverService;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Long addDriver(Driver driver) {
		try {
			Driver savedDriver = driverRepository.saveAndFlush(driver);
			return savedDriver.getId();
		} catch (DatabaseException e) {
			return (long) -1;
		}

	}

	@Override
	public void delete(long id) {
		driverRepository.delete(id);
	}

	@Override
	public boolean editDriver(Driver driver) {
		return driverRepository.saveAndFlush(driver) == null;
	}

	@Override
	public List<Driver> getAll() {
		return driverRepository.findAll();
	}

	@Override
	public Driver getByName(String name) {
		return driverRepository.findByName(name);
	}

}
