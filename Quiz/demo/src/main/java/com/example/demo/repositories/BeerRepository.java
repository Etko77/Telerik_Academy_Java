package com.example.demo.repositories;

import com.example.demo.models.Beer;

public interface BeerRepository {
    Beer getBeer(int beerId);
    void addBeer(Beer beer);
}
