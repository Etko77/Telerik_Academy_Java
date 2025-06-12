package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.exceptions.InvalidDataProvided;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.regex.DataValidator;



public class ProductImpl implements Product {

    private String name;
    private String brand;
    private double price;
    private final GenderType gender;

    public ProductImpl(String name, String brand, double price, GenderType gender) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        DataValidator.validateName(name);
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        DataValidator.validateBrand(brand);
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        try{
            if(price < 0){
                throw new InvalidDataProvided("Invalid price provided");
            }
        }finally{}

        this.price = price;
    }

    public GenderType getGender() {
        return gender;
    }

    @Override
    public String print() {
        return String.format(
                "#%s %s%n" +
                " #Price: $%.2f%n" +
                " #Gender: %s%n",
                name,
                brand,
                price,
                gender);
    }

}
