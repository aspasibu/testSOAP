package com.tsystems.logitest.webservices;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.tsystems.logitest.entity.Driver;

@WebService
public interface DriverWebService {

	public Long addDriver(Driver driver);

	public boolean editDriver(Driver driver);

	public void deleteDriver(@WebParam(name="id") Long idDriver);
}
