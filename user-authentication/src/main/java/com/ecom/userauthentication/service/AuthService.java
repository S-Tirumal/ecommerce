package com.ecom.userauthentication.service;

import com.ecom.userauthentication.dto.LoginResponseDto;
import com.ecom.userauthentication.dto.SignUpRequestDto;
import com.ecom.userauthentication.dto.SignUpResponseDto;

public interface AuthService {
    public SignUpResponseDto signUp(SignUpRequestDto signUpDto);

    public LoginResponseDto login(String username, String password) throws Exception;
}
