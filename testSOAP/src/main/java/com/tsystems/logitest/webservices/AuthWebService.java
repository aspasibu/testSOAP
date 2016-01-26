package com.tsystems.logitest.webservices;

import javax.jws.WebService;

import com.tsystems.logitest.service.errors.AuthErrorType;

@WebService
public interface AuthWebService {
    
    AuthErrorType logIn(String userName, String password);
    
    AuthErrorType logOut(String userName);

}
