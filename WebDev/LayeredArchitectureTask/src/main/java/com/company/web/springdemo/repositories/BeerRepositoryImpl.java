package com.company.web.springdemo.repositories;


import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.Beer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeerRepositoryImpl implements BeerRepository {
    private final List<Beer> beers;

    public BeerRepositoryImpl(){
        beers = new ArrayList<>();

        beers.add(new Beer(4,"Heineken",4.5));
        beers.add(new Beer(8,"Zagorka",4.3));
        beers.add(new Beer(12,"Budweiser",5.6));
    }

    @Override
    public List<Beer> get() {return beers;}

    @Override
    public Beer get(int id) {
        return beers.stream()
                .filter(beer -> beer.getId() == id)
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("Beer with id " + id + " not found"));
    }

    @Override
    public Beer get(String name) {
        return beers.stream()
                .filter(beer -> beer.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("Beer with name " + name + " not found"));
    }

    @Override
    public void create(Beer beer) {
        beers.add(beer);
    }

    @Override
    public void update(int id,Beer beer) {
        Beer beerToUpdate = get(id);
        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());

    }

    @Override
    public void delete(int id) {
        Beer beerToDelete = get(id);
        beers.remove(beerToDelete);
    }
}
