package org.aspasibu.logitest.webservices;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.aspasibu.logitest.types.DutyResponseType;

@WebService
public interface DutyWebService {

	DutyResponseType logIn(@WebParam(name = "username") String userName, @WebParam(name = "password") String password);

	DutyResponseType logOut(@WebParam(name = "username") String userName);

}
