package com.example.demo.models;

import com.example.demo.controllers.BeerRestController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;



@Getter
public class BeerImpl implements Beer {
    @Setter
    @NonNull
    private String beerName;
    @Setter
    @NonNull
    private int beerId;
    @NonNull
    private double abv;

    public BeerImpl(String beerName, int beerId, double abv) {
        this.beerName = beerName;
        this.beerId = beerId;
        setAbv(abv);
    }

    public void setAbv(double abv) {
        if(abv > 0){
            this.abv = abv;
        }else{
            throw new IllegalArgumentException("abv can't be less than 0");
        }

    }
}
