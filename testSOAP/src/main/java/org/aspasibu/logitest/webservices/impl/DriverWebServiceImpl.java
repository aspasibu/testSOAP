package org.aspasibu.logitest.webservices.impl;

import javax.jws.WebService;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.service.DriverService;
import org.aspasibu.logitest.webservices.DriverWebService;

@WebService(endpointInterface = "org.aspasibu.logitest.webservices.DriverWebService")
public class DriverWebServiceImpl implements DriverWebService {

	private DriverService ds;

	@Override
	public String deleteDriver(Long idDriver) {
		return ds.deleteDriver(idDriver);
	}

	@Override
	public String addDriver(String surname, String name, String username, String password) {
		return ds.addDriver(surname, name, username, password);
	}

	@Override
	public String editDriver(Driver driver) {
		return ds.editDriver(driver);
	}

	/**
	 * @return the ds
	 */
	public DriverService getDs() {
		return ds;
	}

	/**
	 * @param ds
	 *            the ds to set
	 */
	public void setDs(DriverService ds) {
		this.ds = ds;
	}

}
