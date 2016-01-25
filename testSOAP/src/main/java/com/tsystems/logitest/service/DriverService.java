package com.tsystems.logitest.service;

import java.util.List;

import com.tsystems.logitest.entity.Driver;

public interface DriverService {

	Long addDriver(Driver driver);

	void delete(long id);

	Driver getByName(String name);

	Driver editDriver(Driver driver);

	List<Driver> getAll();
}
