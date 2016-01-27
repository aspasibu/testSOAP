package com.tsystems.logitest.webservices;

import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HosWebService {
	String calculate(@WebParam(name = "userName") String username, @WebParam(name = "startPeriod") Date startPeriod,
			@WebParam(name = "endPeriod") Date endPeriod);

	Date test();
}
