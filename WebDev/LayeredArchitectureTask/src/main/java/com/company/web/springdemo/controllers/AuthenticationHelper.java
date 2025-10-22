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
    private static final String USERNAME_HEADER = "Username";
    private static final String PASSWORD_HEADER = "Password";

    public AuthenticationHelper(UserService userService) {
        this.userService = userService;
    }
    public User tryGetUser(HttpHeaders headers) {
        if(!headers.containsKey(USERNAME_HEADER) || !headers.containsKey(PASSWORD_HEADER)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"The request resource requires authentication" );
        }
        try{
            String username = headers.getFirst(USERNAME_HEADER);
            String password = headers.getFirst(PASSWORD_HEADER);
            if(userService.validateUser(password)){
                return userService.getUserByUsername(username);
            }else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Incorrect authenthication");
            }
        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid username");
        }
    }
}
