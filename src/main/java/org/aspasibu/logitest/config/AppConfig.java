package org.aspasibu.logitest.config;

import org.aspasibu.logitest.webservices.DriverWebService;
import org.aspasibu.logitest.webservices.DutyWebService;
import org.aspasibu.logitest.webservices.HosWebService;
import org.aspasibu.logitest.webservices.impl.DriverWebServiceImpl;
import org.aspasibu.logitest.webservices.impl.DutyWebServiceImpl;
import org.aspasibu.logitest.webservices.impl.HosWebServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author aspasibu
 * 
 *         Spring configuration class
 *
 */
@Configuration
@ComponentScan(basePackages = "org.aspasibu.logitest")
public class AppConfig {

	/**
	 * Services (and Web services)
	 */

	@Bean
	public DriverWebService driverBean() {
		return new DriverWebServiceImpl();
	}

	@Bean
	public DutyWebService dutyBean() {
		return new DutyWebServiceImpl();
	}

	@Bean
	public HosWebService hosBean() {
		return new HosWebServiceImpl();
	}

}
