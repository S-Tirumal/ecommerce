package com.ecom.userauthentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private String token;

    private SessionState state;

    @ManyToOne
    User user;

    Date expirationDate;
}
