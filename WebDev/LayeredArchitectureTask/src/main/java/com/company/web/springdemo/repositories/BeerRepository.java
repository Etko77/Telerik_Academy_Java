package com.company.web.springdemo.repositories;

import com.company.web.springdemo.models.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> get();

    Beer get(int id);

    Beer get(String name);

    void create(Beer beer);

    void update(int id,Beer beer);

    void delete(int id);
}
