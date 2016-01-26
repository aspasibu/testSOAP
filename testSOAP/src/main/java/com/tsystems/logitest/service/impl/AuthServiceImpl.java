package com.tsystems.logitest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.tsystems.logitest.entity.AuthEvents;
import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.entity.enums.EventType;
import com.tsystems.logitest.repository.AuthRepository;
import com.tsystems.logitest.repository.DriverRepository;
import com.tsystems.logitest.service.AuthService;
import com.tsystems.logitest.service.errors.AuthErrorType;

public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private DriverRepository driverRepository;
    
    @Autowired
    private AuthRepository authEventsRepository;

    @Override
    public AuthErrorType logIn(String userName, String password) {	
	
	Driver driver = driverRepository.findByUserName(userName);
	if (driver == null) {
	    return AuthErrorType.USER_NOT_FOUND;
	}
	
	if (!password.equals(driver.getPassword())) {
	    return AuthErrorType.WRONG_PASSWORD;
	}
	
	AuthEvents event = new AuthEvents();
	event.setDate(new Date());
	event.setDriver(driver);
	event.setType(EventType.LOGIN);
	authEventsRepository.saveAndFlush(event);	
	
	Pageable pages = new PageRequest(0, 1, Direction.DESC,"date");
	Page<AuthEvents> events = authEventsRepository.findAll(pages);
	
	return AuthErrorType.SECOND_ATTEMPT_LOGIN;
    }

    @Override
    public AuthErrorType logOut(String userName) {
	// TODO Auto-generated method stub
	return AuthErrorType.SECOND_ATTEMPT_LOGIN;
    }

}
