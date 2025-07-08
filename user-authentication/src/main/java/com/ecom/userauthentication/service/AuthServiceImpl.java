package com.ecom.userauthentication.service;

import com.ecom.userauthentication.dto.LoginResponseDto;
import com.ecom.userauthentication.dto.SignUpRequestDto;
import com.ecom.userauthentication.dto.SignUpResponseDto;
import com.ecom.userauthentication.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecom.userauthentication.repository.UserRepository;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SecretKey secretKey;

    public SignUpResponseDto signUp(SignUpRequestDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setRole(signUpDto.getRole());
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        user = userRepository.save(user);
        return SignUpResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .message("User registered successfully")
                .build();
    }

    public LoginResponseDto login(String username, String password) throws Exception {
        if(!userRepository.existsByUsername(username)){
            throw new Exception("Username or password is incorrect. " );
        }
        User user = userRepository.findByUsername(username);
        //Here order in which the password is passed matters.
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new Exception("Username or password is incorrect. " );
        }
        Map<String,Object> claims  = new HashMap<>();
        Long currentTimeInMillis = System.currentTimeMillis();
        claims.put("iat",currentTimeInMillis);
        claims.put("exp",currentTimeInMillis+864000);
        claims.put("user_id",user.getId());
        claims.put("issuer","ecommerce-app");
        claims.put("role", user.getRole());

        String token  = Jwts.builder().claims(claims).signWith(secretKey).compact();
        return LoginResponseDto.builder()
                .token(token) // Note: Password should not be returned in a real application
                .build();
    }
}
