package com.company.web.springdemo.services;

import com.company.web.springdemo.models.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> get();

    Beer get(int id);

    void create(Beer beer);

    void update(int id,Beer beer);

    void delete(int id);
}
