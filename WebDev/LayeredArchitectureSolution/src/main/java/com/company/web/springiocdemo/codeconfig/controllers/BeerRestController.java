package com.company.web.springiocdemo.codeconfig.controllers;

import com.company.web.springiocdemo.codeconfig.exceptions.EntityDuplicateException;
import com.company.web.springiocdemo.codeconfig.exceptions.EntityNotFoundException;
import com.company.web.springiocdemo.codeconfig.models.Beer;
import com.company.web.springiocdemo.codeconfig.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {

    private final BeerService service;

    @Autowired
    public BeerRestController(BeerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beer> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    public Beer get(@PathVariable int id) {
        try {
            return service.get(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer) {
        try {
            service.create(beer);
            return beer;
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable int id, @Valid @RequestBody Beer beer) {
        try {
            beer.setId(id);
            service.update(beer);
            return beer;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        try {
            service.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
