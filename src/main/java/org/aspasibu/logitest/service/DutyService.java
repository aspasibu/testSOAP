package org.aspasibu.logitest.service;

import org.aspasibu.logitest.types.DutyResponseType;

public interface DutyService {

	public DutyResponseType logIn(String userName, String password);

	public DutyResponseType logOut(String userName);
}
