package org.aspasibu.logitest.test;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.entity.DutyEvents;
import org.aspasibu.logitest.entity.enums.EventType;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.repository.DutyRepository;
import org.aspasibu.logitest.service.DutyService;
import org.aspasibu.logitest.service.impl.DutyServiceImpl;
import org.aspasibu.logitest.types.DutyResponseType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

public class TestDutyService {

	private DutyRepository dutyRepository;
	private DriverRepository driverRepository;

	private DutyService dutyService;

	private final String USER_NAME = "IVANOV";
	private final String password = "pass";
	private Driver driver;
	private DutyEvents login;
	private DutyEvents logout;

	@Before
	public void setUp() throws Exception {
		dutyRepository = createNiceMock(DutyRepository.class);
		driverRepository = createNiceMock(DriverRepository.class);
		dutyService = new DutyServiceImpl();
		ReflectionTestUtils.setField(dutyService, "driverRepository", driverRepository);
		ReflectionTestUtils.setField(dutyService, "dutyEventsRepository", dutyRepository);

		driver = new Driver("", "", USER_NAME, password);
		login = new DutyEvents();
		login.setType(EventType.LOGIN);
		logout = new DutyEvents();
		logout.setType(EventType.LOGOUT);

		expect(driverRepository.findByUserName(USER_NAME)).andReturn(driver).anyTimes();
		replay(driverRepository);
	}

	@Test
	public void testLogIn() {

		List<DutyEvents> events = new ArrayList<>();
		events.add(login);

		expect(dutyRepository.getEventsByDriver(driver, new PageRequest(0, 1))).andReturn(null);
		expect(dutyRepository.getEventsByDriver(driver, new PageRequest(0, 1))).andReturn(events);
		expect(dutyRepository.saveAndFlush(isA(DutyEvents.class))).andReturn(new DutyEvents());
		replay(dutyRepository);

		assertEquals("test login", dutyService.logIn(null, password), DutyResponseType.USER_NOT_FOUND);
		assertEquals("test login", dutyService.logIn(USER_NAME, null), DutyResponseType.WRONG_PASSWORD);
		assertEquals("test login", dutyService.logIn(USER_NAME, password), DutyResponseType.SUCCESSFULL);
		assertEquals("test login", dutyService.logIn(USER_NAME, password), DutyResponseType.SECOND_ATTEMPT_LOGIN);
		verify(driverRepository);
		verify(dutyRepository);
	}

	@Test
	public void testLogOut() {
		List<DutyEvents> events = new ArrayList<>();
		events.add(logout);

		expect(dutyRepository.getEventsByDriver(driver, new PageRequest(0, 1))).andReturn(null);
		expect(dutyRepository.getEventsByDriver(driver, new PageRequest(0, 1))).andReturn(events);
		expect(dutyRepository.saveAndFlush(isA(DutyEvents.class))).andReturn(new DutyEvents());
		replay(dutyRepository);

		assertEquals("test logout", dutyService.logOut(null), DutyResponseType.USER_NOT_FOUND);
		assertEquals("test logout", dutyService.logOut(USER_NAME), DutyResponseType.SUCCESSFULL);
		assertEquals("test logout", dutyService.logOut(USER_NAME), DutyResponseType.SECOND_ATTEMPT_LOGOUT);
		verify(driverRepository);
		verify(dutyRepository);
	}

}
