package com.ecom.userauthentication.repository;

import com.ecom.userauthentication.model.Session;
import com.ecom.userauthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findSessionByToken(String token);
}
