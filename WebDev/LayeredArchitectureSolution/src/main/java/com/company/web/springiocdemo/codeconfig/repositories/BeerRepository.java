package com.company.web.springiocdemo.codeconfig.repositories;

import com.company.web.springiocdemo.codeconfig.models.Beer;

import java.util.List;

public interface BeerRepository {

    List<Beer> get();

    Beer get(int id);

    Beer get(String name);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);

}
