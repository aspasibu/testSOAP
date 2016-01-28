package org.aspasibu.logitest.webservices;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.aspasibu.logitest.entity.Driver;

@WebService
public interface DriverWebService {

	public Long addDriver(@WebParam(name = "driver") Driver driver);

	public boolean editDriver(@WebParam(name = "driver") Driver driver);

	public void deleteDriver(@WebParam(name = "id") Long idDriver);
}
