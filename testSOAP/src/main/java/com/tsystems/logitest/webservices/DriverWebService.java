package com.tsystems.logitest.webservices;

import javax.jws.WebService;

@WebService(endpointInterface = "webApp.IDriverWebService")
public class DriverWebService implements IDriverWebService {

	public int addDriver(String name, String surname) {
		return 1;
	}

	public boolean editDriver(int idDriver, String name, String surname) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteDriver(int idDriver) {
		// TODO Auto-generated method stub
		return false;
	}

}
