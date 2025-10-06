package com.company.web.springdemo.services;

import com.company.web.springdemo.models.User;
import com.company.web.springdemo.repositories.UserRepoImpl;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(int id);

    User getUserByUsername(String username);
}
