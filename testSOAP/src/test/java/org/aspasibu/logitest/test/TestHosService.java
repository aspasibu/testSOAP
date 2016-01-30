package org.aspasibu.logitest.test;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.expectLastCall;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspasibu.logitest.LogiTestUtils;
import org.aspasibu.logitest.entity.Driver;
import org.aspasibu.logitest.entity.DutyEvents;
import org.aspasibu.logitest.entity.enums.EventType;
import org.aspasibu.logitest.repository.DriverRepository;
import org.aspasibu.logitest.repository.DutyRepository;
import org.aspasibu.logitest.service.HosService;
import org.aspasibu.logitest.service.impl.HosServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class TestHosService {

	private DutyRepository dutyRepository;
	private DriverRepository driverRepository;
	private HosService hosService;

	@Before
	public void setUp() throws Exception {
		dutyRepository = createNiceMock(DutyRepository.class);
		driverRepository = createNiceMock(DriverRepository.class);
		hosService = new HosServiceImpl();
	}

	@Test
	public void testCalculate() {
		expect(driverRepository.findByUserName("")).andReturn(null).times(1);		
		expect(driverRepository.findByUserName("")).andReturn(new Driver()).anyTimes();
        Date startPeriod = new Date(0);
        Date endPeriod = new Date(15);
        Driver driver = new Driver();
        DutyEvents event1 = new DutyEvents(driver, EventType.LOGIN, new Date(5));
        DutyEvents event2 = new DutyEvents(driver, EventType.LOGOUT, new Date(10));
		List<DutyEvents> events = new ArrayList<>();
		events.add(event1);
		events.add(event2);
		expect(dutyRepository.getEventsForPeriod("", startPeriod, endPeriod)).andReturn(events);
		expect(dutyRepository.getEventsForPeriod("", null, null)).andReturn(null);
		replay(driverRepository);
		replay(dutyRepository);
		
		((HosServiceImpl) hosService).setDriverRepository(driverRepository);
		((HosServiceImpl) hosService).setDutyEventsRepository(dutyRepository);
		
		assertEquals(hosService.calculate("", startPeriod, endPeriod), "USER NOT FOUND");
		assertEquals(hosService.calculate("", startPeriod, endPeriod), LogiTestUtils.convertMillisToStrHours(5));
		assertEquals(hosService.calculate("", null, null), LogiTestUtils.convertMillisToStrHours(0));
		
		verify(driverRepository);
		verify(dutyRepository);
	}

}
