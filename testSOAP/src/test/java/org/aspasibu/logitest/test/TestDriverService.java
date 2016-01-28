package org.aspasibu.logitest.test;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.service.impl.DriverServiceImpl;
import org.aspasibu.logitest.types.DriverResponseType;
import org.junit.Before;
import org.junit.Test;

public class TestDriverService {
	private DriverRepository driverRepository;
	private DriverServiceImpl driverService;

	@Before
	public void setUp() throws Exception {
		driverRepository = createNiceMock(DriverRepository.class);
		driverService = new DriverServiceImpl();
	}

	@Test
	public void testAddDriver() {
		Driver driver = new Driver("surname", "name", "username", "pass");
		driver.setId(new Long(1));
		expect(driverRepository.saveAndFlush(driver)).andReturn(driver);
		replay(driverRepository);

		driverService.setDriverRepository(driverRepository);

		assertEquals("Message", driverService.addDriver("surname", "name", "username", "pass"), new Long(1));
		assertEquals("Message", driverService.editDriver(driver), DriverResponseType.SICCESSFULLY_EDITED);
	}

	@Test
	public void testDeleteDriver() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditDriver() {
		fail("Not yet implemented");
	}

}
