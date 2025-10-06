package com.company.web.springdemo.repositories;

import com.company.web.springdemo.models.User;

import java.util.List;

public interface UserRepo {
    List<User> getUsers();

    User getUser(int id);

    User getUserByUsername(String username);
}
