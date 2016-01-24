package com.tsystems.logitest.webservices;

import javax.jws.WebService;

@WebService
public interface IDriverWebService {
	
	public int addDriver(String name, String surname);

	public boolean editDriver(int idDriver, String name, String surname);

	public boolean deleteDriver(int idDriver);
}
