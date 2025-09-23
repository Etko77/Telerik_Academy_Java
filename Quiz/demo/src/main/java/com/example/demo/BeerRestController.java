package com.example.demo;

import models.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/beerApi")
public class BeerRestController {
    private List<Beer> beerList;

    public BeerRestController(){
        beerList = new ArrayList<>();
        beerList.add(new Beer("Zagorka",1,5.5));
        beerList.add(new Beer("Zagorka Retro",4,5.4));
        beerList.add(new Beer("Heineken",10,5.3));
    }

    @GetMapping("/beers")
    public List<Beer> getBeers(){
        return beerList;
    }
    @GetMapping("/beers/{id}")
    public Beer getBeer(@PathVariable int id) {
        String errMessage =  "Beer with id "+ id+ " not found";
            return beerList.stream()
                    .filter(beer -> beer.getBeerId() == id)
                    .findFirst()
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, errMessage));

    }
    @PutMapping("/beers/{id}")
    public void updateBeer(@PathVariable int id, @RequestBody Beer newBeer) {
        Beer beer = beerList.stream()
                .filter(b -> b.getBeerId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Beer with id " + id + " not found"
                ));
        beer.setBeerName(newBeer.getBeerName());
        beer.setBeerId(newBeer.getBeerId());
        beer.setAbv(newBeer.getAbv());
    }
    @PostMapping("/beers")
    public Beer addBeer(@RequestBody Beer beer){
        beerList.add(beer);
        return beer;
    }
    @DeleteMapping("/beers/{id}")
    public void deleteBeer(@PathVariable int id){
        String errMessage =  "Beer with id "+ id+ " not found";
        if(!beerList.removeIf(beer -> beer.getBeerId() == id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errMessage);
        }
    }
}
