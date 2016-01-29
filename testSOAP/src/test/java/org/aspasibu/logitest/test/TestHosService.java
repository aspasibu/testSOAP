package org.aspasibu.logitest.test;

import static org.easymock.EasyMock.createNiceMock;

import org.aspasibu.logitest.repository.DutyRepository;
import org.aspasibu.logitest.service.HosService;
import org.aspasibu.logitest.service.impl.HosServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class TestHosService {

	private DutyRepository dutyRepository;
	private HosService hosService;

	@Before
	public void setUp() throws Exception {
		dutyRepository = createNiceMock(DutyRepository.class);
		hosService = new HosServiceImpl();
	}

	@Test
	public void testCalculate() {
		// expect(dutyRepository.getEventsForPeriod("", new Date(), new
		// Date())).andReturn(value)
	}

}
