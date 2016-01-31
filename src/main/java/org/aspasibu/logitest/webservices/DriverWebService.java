package org.aspasibu.logitest.webservices;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.aspasibu.logitest.entity.Driver;

@WebService
public interface DriverWebService {

	public String addDriver(@WebParam(name = "surname") String surname, @WebParam(name = "name") String name,
			@WebParam(name = "username") String username, @WebParam(name = "password") String password);

	public String editDriver(@WebParam(name = "driver") Driver driver);

	public String deleteDriver(@WebParam(name = "id") Long idDriver);
}
