package com.company.web.springdemo.controllers;

import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.User;
import com.company.web.springdemo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthenticationHelper {
    private final UserService userService;
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    public AuthenticationHelper(UserService userService) {
        this.userService = userService;
    }
    public User tryGetUser(HttpHeaders headers) {
        if(!headers.containsKey(AUTHORIZATION_HEADER_NAME)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Thr request resource requires authentication" );
        }
        try{
            String username = headers.getFirst(AUTHORIZATION_HEADER_NAME);
            return userService.getUserByUsername(username);
        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid username");
        }
    }
}
