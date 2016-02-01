package org.aspasibu.logitest.webservices.impl;

import java.util.Date;

import javax.jws.WebService;

import org.aspasibu.logitest.service.HosService;
import org.aspasibu.logitest.webservices.HosWebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface = "org.aspasibu.logitest.webservices.HosWebService")
public class HosWebServiceImpl implements HosWebService {

	@Autowired
	private HosService hs;

	@Override
	public String calculate(String username, Date startPeriod, Date endPeriod) {
		return hs.calculate(username, startPeriod, endPeriod);
	}

	@Override
	public Date test() {
		return new Date();
	}

}
