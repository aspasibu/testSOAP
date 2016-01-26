package com.tsystems.logitest.webservices;

import javax.jws.WebService;

import com.tsystems.logitest.service.AuthService;
import com.tsystems.logitest.service.errors.AuthErrorType;

@WebService(endpointInterface = "com.tsystems.logitest.webservices.AuthWebService")
public class AuthWebServiceImpl implements AuthWebService {

    AuthService as;

    @Override
    public AuthErrorType logIn(String userName, String password) {
	return as.logIn(userName, password);
    }

    @Override
    public AuthErrorType logOut(String userName) {
	return as.logOut(userName);
    }

    public AuthService getAs() {
        return as;
    }

    public void setAs(AuthService as) {
        this.as = as;
    }

}
