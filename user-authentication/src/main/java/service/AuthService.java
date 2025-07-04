package service;

import dto.LoginRequestDto;
import dto.LoginResponseDto;
import dto.SignUpRequestDto;
import dto.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface AuthService {
    public SignUpResponseDto signUp(SignUpRequestDto signUpDto);

    public LoginResponseDto login(String username, String password) throws Exception;
}
