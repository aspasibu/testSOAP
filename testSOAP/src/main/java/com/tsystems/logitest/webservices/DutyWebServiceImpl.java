package com.tsystems.logitest.webservices;

import javax.jws.WebService;

import com.tsystems.logitest.service.DutyService;
import com.tsystems.logitest.service.errors.DutyErrorType;

@WebService(endpointInterface = "com.tsystems.logitest.webservices.DutyWebService")
public class DutyWebServiceImpl implements DutyWebService {

	DutyService as;

	@Override
	public DutyErrorType logIn(String userName, String password) {
		return as.logIn(userName, password);
	}

	@Override
	public DutyErrorType logOut(String userName) {
		return as.logOut(userName);
	}

	public DutyService getAs() {
		return as;
	}

	public void setAs(DutyService as) {
		this.as = as;
	}

}
