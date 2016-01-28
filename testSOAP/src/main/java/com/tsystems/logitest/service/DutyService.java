package com.tsystems.logitest.service;

import com.tsystems.logitest.service.errors.DutyErrorType;

public interface DutyService {
    
    DutyErrorType logIn(String userName, String password);

    DutyErrorType logOut(String userName);
}
