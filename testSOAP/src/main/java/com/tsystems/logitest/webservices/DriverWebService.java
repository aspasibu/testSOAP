package com.tsystems.logitest.webservices;

import javax.jws.WebService;

import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.webservices.request.RequestAddDriver;

@SuppressWarnings("restriction")
@WebService
public interface DriverWebService {

	public Long addDriver(RequestAddDriver driverRequest);

	public boolean editDriver(int idDriver, Driver driver);

	public boolean deleteDriver(int idDriver);
}
