package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role; // e.g., "USER", "ADMIN"
    private boolean isActive; // true if the user is active, false if deactivated
    private String profileImageUrl; // URL to the user's profile image, if any
}
