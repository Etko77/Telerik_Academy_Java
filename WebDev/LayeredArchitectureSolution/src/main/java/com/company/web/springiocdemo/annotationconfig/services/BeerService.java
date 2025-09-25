package com.company.web.springiocdemo.annotationconfig.services;

import com.company.web.springiocdemo.annotationconfig.models.Beer;

import java.util.List;

public interface BeerService {

    List<Beer> get();

    Beer get(int id);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);

}
