package com.company.web.springdemo.services;

import com.company.web.springdemo.exceptions.DuplicateFoundException;
import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }
    @Override
    public List<Beer> get() {
        return beerRepository.get();
    }

    @Override
    public Beer get(int id) {
        return beerRepository.get(id);
    }

    @Override
    public void create(Beer beer) {
        boolean duplicateExists = true;
        try{
            beerRepository.get(beer.getId());
        }catch(DuplicateFoundException e){
            duplicateExists = false;
        }
        if(duplicateExists){
            throw new DuplicateFoundException("Beer with id " + beer.getId() + " already exists");
        }
        beerRepository.create(beer);
    }

    @Override
    public void update(int id, Beer beer) {
        boolean duplicateExists = true;
        try{
            beerRepository.get(id);
            if(beerRepository.get(id).getId() == beer.getId()){
                duplicateExists = false;
            }
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException("Beer with id " + id + " not found");
        }
        if(duplicateExists){
            throw new DuplicateFoundException("Beer with id " + beer.getId() + " already exists");
        }
        beerRepository.update(id, beer);
    }

    @Override
    public void delete(int id) {
        beerRepository.delete(id);
    }
}
