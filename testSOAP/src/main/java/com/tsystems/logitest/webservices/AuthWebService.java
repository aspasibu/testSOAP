package com.tsystems.logitest.webservices;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.tsystems.logitest.service.errors.AuthErrorType;

@WebService
public interface AuthWebService {

	AuthErrorType logIn(@WebParam(name = "username") String userName, @WebParam(name = "password") String password);

	AuthErrorType logOut(@WebParam(name = "username") String userName);

}
