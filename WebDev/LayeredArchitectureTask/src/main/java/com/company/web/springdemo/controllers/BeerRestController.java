package com.company.web.springdemo.controllers;

import com.company.web.springdemo.exceptions.DuplicateFoundException;
import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.exceptions.UnathorizedOperationException;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.User;
import com.company.web.springdemo.services.BeerService;
import com.company.web.springdemo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {

    private final BeerService beerService;
    private final AuthenticationHelper authenticationHelper;

    public BeerRestController(BeerService beerService, AuthenticationHelper authenticationHelper) {
       this.beerService = beerService;
       this.authenticationHelper = authenticationHelper;
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
    public Beer create(@RequestHeader HttpHeaders headers, @Valid @RequestBody Beer beer) {
        try{
            User user = authenticationHelper.tryGetUser(headers);
            beerService.create(beer);
            return beerService.get(beer.getId());
        }catch(EntityNotFoundException e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch(DuplicateFoundException e){
            throw new  ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer update(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody Beer beer) {
        try{
            User user = authenticationHelper.tryGetUser(headers);
            beer.setId(id);
            beerService.update(id, beer, user);
        }catch(EntityNotFoundException e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch(DuplicateFoundException e){
            throw new  ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }catch (UnathorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return beer;
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            beerService.delete(id,user);
        }catch(EntityNotFoundException e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }



}
