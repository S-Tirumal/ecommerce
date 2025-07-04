package service;

import dto.LoginResponseDto;
import dto.SignUpRequestDto;
import dto.SignUpResponseDto;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository for database operations

    public SignUpResponseDto signUp(SignUpRequestDto signUpDto) {
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setRole(signUpDto.getRole());
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setPassword(signUpDto.getPassword());

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
            throw new Exception("User not found with username: " + username);
        }
        User user = userRepository.findByUsernameAndPassword(username, password);
        return LoginResponseDto.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .token(user.getUsername()+":"+user.getPassword()) // Note: Password should not be returned in a real application
                .build();
    }
}
