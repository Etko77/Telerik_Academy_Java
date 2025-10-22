package com.company.web.springdemo.services;

import com.company.web.springdemo.exceptions.DuplicateFoundException;
import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.exceptions.UnathorizedOperationException;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.User;
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
    public void create(User user, Beer beer) {
        boolean duplicateExists = false;
        if(!user.isAdmin()){
            throw new UnathorizedOperationException("User with id " + user.getId() + " is not admin");
        }
        try{
            if(beerRepository.get().stream().anyMatch(beer1 -> beer1.getId() == beer.getId())){
                duplicateExists = true;
            }
        }catch(DuplicateFoundException e){
            duplicateExists = false;
        }
        if(duplicateExists){
            throw new DuplicateFoundException("Beer with id " + beer.getId() + " already exists");
        }
        beerRepository.create(beer);
    }

    @Override
    public void update(int id, Beer beer, User user) {

        if(!user.isAdmin()){
            throw new UnathorizedOperationException("User with id " + user.getId() + " is not admin");
        }
        boolean duplicateExists = true;
        try{
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
    public void delete(int id, User user) {
        if(!user.isAdmin()){
            throw new UnathorizedOperationException("User with id " + user.getId() + " is not admin");
        }
        beerRepository.delete(id);
    }
}
