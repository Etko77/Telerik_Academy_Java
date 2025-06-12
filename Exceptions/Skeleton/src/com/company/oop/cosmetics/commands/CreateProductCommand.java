package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidDataProvided;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;

import java.util.List;

public class CreateProductCommand implements Command {

    private static final String PRODUCT_CREATED = "Product with name %s was created!";

    private final ProductRepository productRepository;

    public CreateProductCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        //TODO Validate parameters count

        if (parameters.size() < 4) {
            throw new IllegalArgumentException("Not enough parameters provided");
        }
        String name = parameters.get(0);
        String brand = parameters.get(1);

        //TODO Validate price format
        double price;
        try {
            price = Double.parseDouble(parameters.get(2));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format: " + parameters.get(2));
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        //TODO Validate gender format
        GenderType gender;
        try {
            gender = GenderType.valueOf(parameters.get(3).toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Incorrect gender data: " + parameters.get(3));
        }


        return createProduct(name, brand, price, gender);
    }

    private String createProduct(String name, String brand, double price, GenderType gender) {
        //TODO Ensure product name is unique
        if(productRepository.findProductByName(name) == null){
            productRepository.createProduct(name, brand, price, gender);
            return String.format(PRODUCT_CREATED, name);

        }
        if(productRepository.findProductByName(name)
                .getName().equals(name)){
            throw new InvalidDataProvided("This product already exists");
        }

        return "none";
    }

}
