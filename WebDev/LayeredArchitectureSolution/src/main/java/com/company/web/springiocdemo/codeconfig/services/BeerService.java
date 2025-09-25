package com.company.web.springiocdemo.codeconfig.services;

import com.company.web.springiocdemo.codeconfig.models.Beer;

import java.util.List;

public interface BeerService {

    List<Beer> get();

    Beer get(int id);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);

}
