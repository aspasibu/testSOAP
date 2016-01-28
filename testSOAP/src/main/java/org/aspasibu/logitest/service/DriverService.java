package org.aspasibu.logitest.service;

import java.util.List;

import org.aspasibu.logitest.entity.Driver;

public interface DriverService {

	Long addDriver(Driver driver);

	void delete(long id);

	Driver getByName(String name);

	boolean editDriver(Driver driver);

	List<Driver> getAll();
}
