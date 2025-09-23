package models;

import org.springframework.lang.NonNull;

public class Beer {
    @NonNull
    private String beerName;
    @NonNull
    private int beerId;
    @NonNull
    private double abv;

    public Beer(String beerName, int beerId, double abv) {
        this.beerName = beerName;
        this.beerId = beerId;
        setAbv(abv);
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        if(abv > 0){
            this.abv = abv;
        }else{
            throw new IllegalArgumentException("abv can't be less than 0");
        }

    }
}
