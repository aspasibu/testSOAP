package com.tsystems.logitest.webservices;

import javax.jws.WebService;

import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.service.DriverService;
import com.tsystems.logitest.webservices.request.RequestAddDriver;

@WebService(endpointInterface = "com.tsystems.logitest.webservices.DriverWebService")
public class DriverWebServiceImpl implements DriverWebService {

	private DriverService ds;

	@Override
	public boolean deleteDriver(int idDriver) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long addDriver(RequestAddDriver driverRequest) {
		return ds.addDriver(driver);
	}

	@Override
	public boolean editDriver(int idDriver, Driver driver) {
		// TODO Auto-generated method stub
		return false;
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
