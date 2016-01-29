package org.aspasibu.logitest.test;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.service.DriverService;
import org.aspasibu.logitest.service.impl.DriverServiceImpl;
import org.aspasibu.logitest.types.DriverResponseType;

import org.junit.Before;
import org.junit.Test;

public class TestDriverService {
	private DriverRepository driverRepository;
	private DriverService driverService;
	private Driver driver;
	private Driver driverResponse;

	@Before
	public void setUp() throws Exception {
		driverRepository = createNiceMock(DriverRepository.class);
		driverService = new DriverServiceImpl();
		driver = new Driver("surname", "name", "username", "pass");
		driverResponse = new Driver("surname", "name", "username", "pass");
		driverResponse.setId(new Long(1));
	}

	@Test
	public void testAddDriver() {

		expect(driverRepository.saveAndFlush(driver)).andReturn(driverResponse).times(1);
		expect(driverRepository.findByUserName("USERNAME")).andReturn(driverResponse);
		expect(driverRepository.saveAndFlush(driver)).andThrow(new RuntimeException());

		replay(driverRepository);

		((DriverServiceImpl) driverService).setDriverRepository(driverRepository);

		assertEquals("Message", driverService.addDriver("surname", "name", "username", "pass"), String.valueOf(1));
		assertEquals("Message", driverService.addDriver("surname", "name", "username", "pass"), DriverResponseType.DATABASE_EXCEPTION.toString());
		assertEquals("Message", driverService.addDriver("", "", "USERNAME", ""), DriverResponseType.ALREADY_CREATED.toString());

		verify(driverRepository);
	}

	@Test
	public void testDeleteDriver() {
		driverRepository.delete((long) 1);
		expectLastCall().times(1);		
		expectLastCall().andThrow(new RuntimeException());
		replay(driverRepository);
		
		((DriverServiceImpl) driverService).setDriverRepository(driverRepository);

		assertEquals("Message", driverService.deleteDriver((long) 1), DriverResponseType.SICCESSFULLY_DELETED.toString());
		assertEquals("Message", driverService.deleteDriver((long) 1), DriverResponseType.DATABASE_EXCEPTION.toString());		

		verify(driverRepository);
	}

	@Test
	public void testEditDriver() {
		
		expect(driverRepository.saveAndFlush(driver)).andReturn(driverResponse).times(1);
		expect(driverRepository.saveAndFlush(driver)).andThrow(new RuntimeException());

		replay(driverRepository);
		((DriverServiceImpl) driverService).setDriverRepository(driverRepository);

		assertEquals("Message", driverService.editDriver(driver), DriverResponseType.SICCESSFULLY_EDITED.toString());
		assertEquals("Message", driverService.editDriver(driver), DriverResponseType.DATABASE_EXCEPTION.toString());

		verify(driverRepository);
	}

}
