package org.aspasibu.logitest.test;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.service.DriverService;
import org.aspasibu.logitest.service.impl.DriverServiceImpl;
import org.aspasibu.logitest.types.DriverResponseType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class TestDriverService {
	private DriverRepository driverRepository;
	private DriverService driverService;
	private Driver driver;
	private Driver driverResponse;

	@Before
	public void setUp() throws Exception {
		driverRepository = createNiceMock(DriverRepository.class);
		driverService = new DriverServiceImpl();
		ReflectionTestUtils.setField(driverService, "driverRepository", driverRepository);

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

		assertEquals("add driver - success", driverService.addDriver("surname", "name", "username", "pass"),
				String.valueOf(1));
		assertEquals("add driver - database exception", driverService.addDriver("surname", "name", "username", "pass"),
				DriverResponseType.DATABASE_EXCEPTION.toString());
		assertEquals("add driver - already created", driverService.addDriver("", "", "USERNAME", ""),
				DriverResponseType.ALREADY_CREATED.toString());

		verify(driverRepository);
	}

	@Test
	public void testDeleteDriver() {
		expect(driverRepository.findOne((long) 1)).andReturn(driverResponse).anyTimes();
		driverRepository.delete((long) 1);
		expectLastCall().times(1);
		expectLastCall().andThrow(new RuntimeException());
		replay(driverRepository);

		assertEquals("delete driver - success", driverService.deleteDriver((long) 1),
				DriverResponseType.SICCESSFULLY_DELETED.toString());
		assertEquals("delete driver - database exception", driverService.deleteDriver((long) 1),
				DriverResponseType.DATABASE_EXCEPTION.toString());
		assertEquals("delete driver - user not found", driverService.deleteDriver((long) 2),
				DriverResponseType.USER_NOT_FOUND.toString());

		verify(driverRepository);
	}

	@Test
	public void testEditDriver() {

		expect(driverRepository.saveAndFlush(driverResponse)).andReturn(driverResponse).times(1);
		expect(driverRepository.findOne(driverResponse.getId())).andReturn(driverResponse).anyTimes();
		expect(driverRepository.saveAndFlush(driverResponse)).andThrow(new RuntimeException());

		replay(driverRepository);

		assertEquals("edit driver - wrong request", driverService.editDriver(null),
				DriverResponseType.WRONG_REQUEST.toString());
		assertEquals("edit driver - user not found", driverService.editDriver(driver),
				DriverResponseType.USER_NOT_FOUND.toString());
		assertEquals("edit driver - success", driverService.editDriver(driverResponse),
				DriverResponseType.SICCESSFULLY_EDITED.toString());
		assertEquals("edit driver - database exception", driverService.editDriver(driverResponse),
				DriverResponseType.DATABASE_EXCEPTION.toString());

		verify(driverRepository);
	}

}
