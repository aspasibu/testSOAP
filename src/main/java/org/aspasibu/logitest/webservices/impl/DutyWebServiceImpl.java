package org.aspasibu.logitest.webservices.impl;

import javax.jws.WebService;

import org.aspasibu.logitest.service.DutyService;
import org.aspasibu.logitest.types.DutyResponseType;
import org.aspasibu.logitest.webservices.DutyWebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface = "org.aspasibu.logitest.webservices.DutyWebService")
public class DutyWebServiceImpl implements DutyWebService {

	@Autowired
	private DutyService as;

	@Override
	public DutyResponseType logIn(String userName, String password) {
		return as.logIn(userName, password);
	}

	@Override
	public DutyResponseType logOut(String userName) {
		return as.logOut(userName);
	}
}
