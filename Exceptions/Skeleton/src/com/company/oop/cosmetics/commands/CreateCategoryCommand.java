package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.exceptions.InvalidDataProvided;
import com.company.oop.cosmetics.models.CategoryImpl;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;

import java.awt.*;
import java.util.List;

public class CreateCategoryCommand implements Command {

    private static final String CATEGORY_CREATED = "Category with name %s was created!";

    private final ProductRepository productRepository;

    public CreateCategoryCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        //TODO Validate parameters count DONE
        String categoryNameToAdd;

        try{
            if(parameters.size() < 1){
                throw new IndexOutOfBoundsException("Not enough parameters provided");
            }
            categoryNameToAdd = parameters.get(0);

        }catch(IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        String categoryName = parameters.get(0);

        return createCategory(categoryName);
    }

    private String createCategory(String categoryName) {
        for(Category category:productRepository.getCategories()){
            try{
                if(category.getName().equals(categoryName)){
                    throw new InvalidDataProvided("Category name already exists");
                }
            }catch(InvalidDataProvided e){
                System.out.println(e.getMessage());
            }

        }

        productRepository.createCategory(categoryName);

        return String.format(CATEGORY_CREATED, categoryName);
    }

}
