package com.company.web.springdemo.controllers.mvc;

import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.helpers.BeerMapper;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.BeerDto;
import com.company.web.springdemo.models.FilterOptions;
import com.company.web.springdemo.models.User;
import com.company.web.springdemo.services.BeerService;
import com.company.web.springdemo.services.StyleService;
import com.company.web.springdemo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/beers")
public class BeerMvcController {

    private final BeerService beerService;
    private final UserService userService;
    private final StyleService styleService;
    private final BeerMapper beerMapper;


    @Autowired
    public BeerMvcController(BeerService beerService, UserService userService,
                             StyleService styleService, BeerMapper beerMapper) {
        this.beerService = beerService;
        this.userService = userService;
        this.styleService = styleService;
        this.beerMapper = beerMapper;
    }

    @GetMapping
    public String showAllBeers(Model model) {
        model.addAttribute("beers", beerService.get(new FilterOptions()));
        return "BeersView";
    }

    @GetMapping("/{id}")
    public String showSingleBeer(@PathVariable int id, Model model) {
        try {
            Beer beer = beerService.get(id);
            model.addAttribute("beer", beer);
            return "BeerView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
    @PostMapping("/new")
    public String createBeer(@Valid @ModelAttribute("beer") BeerDto beerDto, User user){
        try{
            beerService.create(beerMapper.fromDto(beerDto), user);
            return "redirect:/beers";
        }catch()

    }


}
