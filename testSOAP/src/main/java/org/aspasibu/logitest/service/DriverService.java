package org.aspasibu.logitest.service;

import org.aspasibu.logitest.entity.Driver;

public interface DriverService {

	public String addDriver(String surname, String name, String username, String password);

	public String editDriver(Driver driver);

	public String deleteDriver(Long idDriver);
}
