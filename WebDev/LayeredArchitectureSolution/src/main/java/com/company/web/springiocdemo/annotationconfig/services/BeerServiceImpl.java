package com.company.web.springiocdemo.annotationconfig.services;

import com.company.web.springiocdemo.annotationconfig.repositories.BeerRepository;
import com.company.web.springiocdemo.annotationconfig.exceptions.EntityDuplicateException;
import com.company.web.springiocdemo.annotationconfig.exceptions.EntityNotFoundException;
import com.company.web.springiocdemo.annotationconfig.models.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeerServiceImpl implements BeerService {
    private final BeerRepository repository;

    @Autowired
    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Beer> get() {
        return repository.get();
    }

    @Override
    public Beer get(int id) {
        return repository.get(id);
    }

    @Override
    public void create(Beer beer) {
        boolean duplicateExists = true;
        try {
            repository.get(beer.getName());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("Beer", "name", beer.getName());
        }

        repository.create(beer);
    }

    @Override
    public void update(int id, Beer beer) {
        boolean duplicateExists = true;
        try {
            Beer existingBeer = repository.get(beer.getName());
            if (existingBeer.getId() == beer.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("Beer", "name", beer.getName());
        }

        repository.update(id, beer);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

}
