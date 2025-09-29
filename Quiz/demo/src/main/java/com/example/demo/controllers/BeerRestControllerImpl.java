package com.example.demo.controllers;

import com.example.demo.models.BeerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/beerApi/beers")
public class BeerRestControllerImpl implements BeerRestController {
    private final List<BeerImpl> beerList;

    public BeerRestControllerImpl(){
        beerList = new ArrayList<>();

        beerList.add(new BeerImpl("Zagorka",1,5.5));
        beerList.add(new BeerImpl("Zagorka Retro",4,5.4));
        beerList.add(new BeerImpl("Heineken",10,5.3));
    }

    @GetMapping
    public List<BeerImpl> getBeers(){
        return beerList;
    }

    @GetMapping("/{id}")
    public BeerImpl getBeer(@PathVariable int id) {
        String errMessage =  "Beer with id "+ id+ " not found";
            return beerList.stream()
                    .filter(beer -> beer.getBeerId() == id)
                    .findFirst()
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, errMessage));

    }
    @PutMapping("/{id}")
    public void updateBeer(@PathVariable int id, @RequestBody BeerImpl newBeer) {
        BeerImpl beer = beerList.stream()
                .filter(b -> b.getBeerId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Beer with id " + id + " not found"
                ));
        beer.setBeerName(newBeer.getBeerName());
        beer.setBeerId(newBeer.getBeerId());
        beer.setAbv(newBeer.getAbv());
    }
    @PostMapping
    public BeerImpl addBeer(@RequestBody BeerImpl beer){
        beerList.add(beer);
        return beer;
    }
    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable int id){
        String errMessage =  "Beer with id "+ id+ " not found";
        if(!beerList.removeIf(beer -> beer.getBeerId() == id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errMessage);
        }
    }
}
