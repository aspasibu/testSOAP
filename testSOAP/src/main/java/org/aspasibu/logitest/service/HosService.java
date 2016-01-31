package org.aspasibu.logitest.service;

import java.util.Date;

import org.aspasibu.logitest.entity.Driver;

public interface HosService {

	public String calculate(String username, Date startPeriod, Date endPeriod);
	
}
