package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpResponseDto implements Serializable {
    private String username;
    private String email;
    private String role; // e.g., "USER", "ADMIN"
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String message; // e.g., "User registered successfully"
}
