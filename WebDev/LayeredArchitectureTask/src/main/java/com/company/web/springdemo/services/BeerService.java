package com.company.web.springdemo.services;

import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.User;

import java.util.List;

public interface BeerService {
    List<Beer> get();

    Beer get(int id);

    void create(User user, Beer beer);

    void update(int id, Beer beer, User user);

    void delete(int id, User user);
}
