package com.tsystems.logitest.webservices;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.tsystems.logitest.service.errors.DutyErrorType;

@WebService
public interface DutyWebService {

	DutyErrorType logIn(@WebParam(name = "username") String userName, @WebParam(name = "password") String password);

	DutyErrorType logOut(@WebParam(name = "username") String userName);

}
