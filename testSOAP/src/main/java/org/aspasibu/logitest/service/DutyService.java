package org.aspasibu.logitest.service;

import org.aspasibu.logitest.service.errors.DutyErrorType;

public interface DutyService {
    
    DutyErrorType logIn(String userName, String password);

    DutyErrorType logOut(String userName);
}
