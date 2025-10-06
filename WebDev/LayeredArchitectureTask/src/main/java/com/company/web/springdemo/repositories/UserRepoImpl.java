package com.company.web.springdemo.repositories;

import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo{

    private final List<User> users;

    public UserRepoImpl() {
        users = new ArrayList<>();
        users.add(new User(1,"Pesho",true));
        users.add(new User(2,"Vanko",true));
        users.add(new User(3,"Ilian",true));
    }
    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(int id) {
        return getUsers().stream()
                .filter(user -> user.getId()== id)
                .findFirst().orElseThrow(()-> new EntityNotFoundException("User not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return getUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst().orElseThrow(()-> new EntityNotFoundException("User not found"));
    }
}
