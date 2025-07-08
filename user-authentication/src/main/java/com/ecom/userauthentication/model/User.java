package com.ecom.userauthentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{
    @Column(unique = true)
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
