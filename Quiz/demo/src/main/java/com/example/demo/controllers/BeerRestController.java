package com.example.demo.controllers;

import com.example.demo.models.BeerImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BeerRestController {
    public List<BeerImpl> getBeers();
    public BeerImpl getBeer(@PathVariable int id);
    public void updateBeer(@PathVariable int id, @RequestBody BeerImpl newBeer);
    public BeerImpl addBeer(@RequestBody BeerImpl beer);
    public void deleteBeer(@PathVariable int id);
}
