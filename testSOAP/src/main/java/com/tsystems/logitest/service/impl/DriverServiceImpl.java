package com.tsystems.logitest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.repository.DriverRepository;
import com.tsystems.logitest.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepository driverRepository;

	public Long addDriver(Driver driver) {
		Driver savedDriver = driverRepository.saveAndFlush(driver);
		return savedDriver.getId();
	}

	public void delete(long id) {
		driverRepository.delete(id);

	}

	public Driver editDriver(Driver driver) {
		return driverRepository.saveAndFlush(driver);
	}

	public List<Driver> getAll() {
		return driverRepository.findAll();
	}

	public Driver getByName(String name) {
		return driverRepository.findByName(name);
	}

}
