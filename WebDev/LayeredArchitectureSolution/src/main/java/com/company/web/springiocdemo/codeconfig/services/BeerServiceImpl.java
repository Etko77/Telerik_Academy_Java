package com.company.web.springiocdemo.codeconfig.services;

import com.company.web.springiocdemo.codeconfig.exceptions.EntityDuplicateException;
import com.company.web.springiocdemo.codeconfig.exceptions.EntityNotFoundException;
import com.company.web.springiocdemo.codeconfig.models.Beer;
import com.company.web.springiocdemo.codeconfig.repositories.BeerRepository;

import java.util.List;

public class BeerServiceImpl implements BeerService {
    private final BeerRepository repository;

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
    public void update(Beer beer) {
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

        repository.update(beer);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

}
