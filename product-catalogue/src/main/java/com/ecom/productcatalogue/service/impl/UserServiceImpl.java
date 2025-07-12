package com.ecom.productcatalogue.service.impl;

import com.ecom.productcatalogue.repository.UserRepository;
import com.ecom.productcatalogue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean validateToken(String token){
        return userRepository.validateToken(token);
    }
}
