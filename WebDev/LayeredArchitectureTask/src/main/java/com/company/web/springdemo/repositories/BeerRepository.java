package com.company.web.springdemo.repositories;

import com.company.web.springdemo.models.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> getBeers();
}
