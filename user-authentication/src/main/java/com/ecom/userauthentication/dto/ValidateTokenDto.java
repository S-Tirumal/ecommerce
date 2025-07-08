package com.ecom.userauthentication.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ValidateTokenDto implements Serializable {
    private String userId;
    private String token;
}
