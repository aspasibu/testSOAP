package org.aspasibu.logitest.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;
import org.aspasibu.logitest.webservices.DriverWebService;
import org.aspasibu.logitest.webservices.DutyWebService;
import org.aspasibu.logitest.webservices.HosWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

	@Bean
	@Autowired
	public Endpoint driverSoapService(DriverWebService driverWebService) {
		Endpoint endpoint = new EndpointImpl(driverWebService);
		endpoint.publish("/driver");
		return endpoint;
	}

	@Bean
	@Autowired
	public Endpoint dutySoapService(DutyWebService dutyWebService) {
		Endpoint endpoint = new EndpointImpl(dutyWebService);
		endpoint.publish("/duty");
		return endpoint;
	}

	@Bean
	@Autowired
	public Endpoint hosSoapService(HosWebService hosWebService) {
		Endpoint endpoint = new EndpointImpl(hosWebService);
		endpoint.publish("/hos");
		return endpoint;
	}

}
