package com.ecom.userauthentication.service;

import com.ecom.userauthentication.dto.LoginResponseDto;
import com.ecom.userauthentication.dto.SignUpRequestDto;
import com.ecom.userauthentication.dto.SignUpResponseDto;
import com.ecom.userauthentication.model.Session;
import com.ecom.userauthentication.model.SessionState;
import com.ecom.userauthentication.model.User;
import com.ecom.userauthentication.repository.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecom.userauthentication.repository.UserRepository;

import javax.crypto.SecretKey;
import java.sql.Date;
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

    @Autowired
    private SessionRepository sessionRepository;

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
        Long expirationTimeInMillis = currentTimeInMillis + 86400000;
        claims.put("iat",currentTimeInMillis);
        claims.put("exp",expirationTimeInMillis);
        claims.put("user_id",user.getId());
        claims.put("issuer","ecommerce-app");
        claims.put("role", user.getRole());

        String token  = Jwts.builder().claims(claims).signWith(secretKey).compact();
        Session session = new Session();
        session.setExpirationDate(new Date(expirationTimeInMillis));
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);
        return LoginResponseDto.builder()
                .token(token) // Note: Password should not be returned in a real application
                .build();
    }

    public boolean isTokenValid(String token) throws Exception {
        if (token == null || token.isEmpty()) {
            throw new Exception("Token is null or empty");
        }
        Session session = sessionRepository.findSessionByToken(token);
        if (session == null) {
            throw new Exception("Session not found for the provided token");
        }
        // Validate the token using the secret key
        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();
        Long expiry = (Long)claims.get("exp");
        Long currentTimeStamp = System.currentTimeMillis();
        if(currentTimeStamp > expiry || session.getExpirationDate().before(new Date(System.currentTimeMillis()))) {
            //Marking session entry as expired
            session.setState(SessionState.EXPIRED);
            sessionRepository.save(session);
            return false;
        }
        return true;
    }
}
