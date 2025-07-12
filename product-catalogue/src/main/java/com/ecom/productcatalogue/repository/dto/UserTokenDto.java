package com.ecom.productcatalogue.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class UserTokenDto implements Serializable {
    private Long userId;
    private String token;
}
