package com.company.web.springiocdemo.annotationconfig.repositories;

import com.company.web.springiocdemo.annotationconfig.exceptions.EntityNotFoundException;
import com.company.web.springiocdemo.annotationconfig.models.Beer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeerRepositoryImpl implements BeerRepository {
    private final List<Beer> beers;

    public BeerRepositoryImpl() {
        beers = new ArrayList<>();
        beers.add(new Beer(1, "Glarus English Ale", 4.6));
        beers.add(new Beer(2, "Rhombus Porter", 5.0));
        beers.add(new Beer(3, "Opasen Char", 6.6));
    }

    @Override
    public List<Beer> get() {
        return beers;
    }

    @Override
    public Beer get(int id) {
        return beers.stream()
                .filter(beer -> beer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }

    @Override
    public Beer get(String name) {
        return beers.stream()
                .filter(beer -> beer.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    @Override
    public void create(Beer beer) {
        beers.add(beer);
    }

    @Override
    public void update(Beer beer) {
        Beer beerToUpdate = get(beer.getId());
        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    @Override
    public void delete(int id) {
        Beer beerToDelete = get(id);
        beers.remove(beerToDelete);
    }

}
