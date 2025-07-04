package controller;

import dto.LoginRequestDto;
import dto.LoginResponseDto;
import dto.SignUpRequestDto;
import dto.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AuthServiceImpl;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/sign_up")
    ResponseEntity<SignUpResponseDto> signUp(SignUpRequestDto signUpDto) {
        SignUpResponseDto responseDto = authService.signUp(signUpDto);
        return new ResponseEntity<SignUpResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        if (loginRequestDto.getUsername() == null || loginRequestDto.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LoginResponseDto loginResponseDto;
        try {
            loginResponseDto = authService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}
