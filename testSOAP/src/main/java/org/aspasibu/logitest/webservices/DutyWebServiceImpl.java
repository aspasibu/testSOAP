package org.aspasibu.logitest.webservices;

import javax.jws.WebService;

import org.aspasibu.logitest.service.DutyService;
import org.aspasibu.logitest.service.errors.DutyErrorType;

@WebService(endpointInterface = "org.aspasibu.logitest.webservices.DutyWebService")
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
