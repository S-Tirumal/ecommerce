package com.ecom.userauthentication.repository;

import com.ecom.userauthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods can be defined here if needed
    User findByUsername(String username);
    boolean existsByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
