package com.company.web.springdemo.controllers;


import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.FilterOptions;
import com.company.web.springdemo.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class BeerMVController {

    private final BeerService beerService;


    @Autowired
    public BeerMVController(BeerService beerService) {
        this.beerService = beerService;
    }
    @GetMapping("beers")
    public String getAllBeers(Model model){
        FilterOptions filterOptions = new FilterOptions(null, null, null, null, null, null);
        List<Beer> beers = beerService.get(filterOptions);
        model.addAttribute("beers",beers);
        return "BeersView";
    }
    @GetMapping("/{id")
    public String showingSingleBeer(@PathVariable Integer id, Model model){
        try{
            Beer beer = beerService.get(id);
            model.addAttribute("beer",beer);
            return "BeersView";
        }catch(EntityNotFoundException e){
            model.addAttribute("error",e.getMessage());
            return "not-found";
        }
    }
}
