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

        try{
            if(parameters.size() < 4) {
                throw new IndexOutOfBoundsException("Not enough parameters provided");
            }

        }catch(IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        String name = parameters.get(0);
        String brand = parameters.get(1);

        //TODO Validate price format
        double price = Double.parseDouble(parameters.get(2));
        if (price < 0){
            price = 0;
        }
        //TODO Validate gender format
        GenderType gender = null;
        try{
            gender = GenderType.valueOf(parameters.get(3).toUpperCase());
        }catch(IllegalArgumentException e){
            System.out.println("Incorrect gender data");
        }


        return createProduct(name, brand, price, gender);
    }

    private String createProduct(String name, String brand, double price, GenderType gender) {
        //TODO Ensure product name is unique
        for(Product product:productRepository.getProducts()){
            try{
                if(product.getName().equals(name)){
                    throw new InvalidDataProvided("Product name already exists");
                }
            }catch(InvalidDataProvided e){
                System.out.println(e.getMessage());
            }

        }
        productRepository.createProduct(name, brand, price, gender);

        return String.format(PRODUCT_CREATED, name);
    }

}
