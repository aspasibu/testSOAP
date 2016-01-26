package com.tsystems.logitest.service;

import com.tsystems.logitest.service.errors.AuthErrorType;

public interface AuthService {
    
    AuthErrorType logIn(String userName, String password);

    AuthErrorType logOut(String userName);
}
