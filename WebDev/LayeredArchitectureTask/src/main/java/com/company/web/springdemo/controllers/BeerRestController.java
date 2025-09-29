package com.company.web.springdemo.controllers;

import com.company.web.springdemo.exceptions.DuplicateFoundException;
import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.services.BeerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {

    private final BeerService beerService;

    public BeerRestController(BeerService beerService) {
       this.beerService = beerService;
    }

    @GetMapping
    public List<Beer> get() {
        return beerService.get();
    }

    @GetMapping("/{id}")
    public Beer get(@PathVariable int id) {
        try{
            return beerService.get(id);
        }catch(EntityNotFoundException e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer) {
        try{
            beerService.create(beer);
            return beerService.get(beer.getId());
        }catch(EntityNotFoundException e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch(DuplicateFoundException e){
            throw new  ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable int id, @Valid @RequestBody Beer beer) {
        try{
            beer.setId(id);
            beerService.update(id, beer);
            return beerService.get(beer.getId());
        }catch(EntityNotFoundException e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch(DuplicateFoundException e){
            throw new  ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        try {
            beerService.delete(id);
        }catch(EntityNotFoundException e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }



}
