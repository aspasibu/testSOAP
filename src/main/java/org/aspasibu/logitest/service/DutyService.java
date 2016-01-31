package org.aspasibu.logitest.service;

import org.aspasibu.logitest.types.DutyResponseType;

public interface DutyService {
    
    DutyResponseType logIn(String userName, String password);

    DutyResponseType logOut(String userName);
}
