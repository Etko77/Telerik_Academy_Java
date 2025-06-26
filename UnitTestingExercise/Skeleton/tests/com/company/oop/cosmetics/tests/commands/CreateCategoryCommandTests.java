package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.core.CommandFactoryImpl;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.CommandFactory;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.CategoryImpl;
import com.company.oop.cosmetics.models.contracts.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateCategoryCommandTests {

    private CreateCategoryCommand comm;
    private ProductRepository productRepository;
    private CommandFactoryImpl commandsFactory;
    @BeforeEach
    public void initializeCommand(){
        productRepository = new ProductRepositoryImpl();
        comm = new CreateCategoryCommand(productRepository);
        commandsFactory = new CommandFactoryImpl();
    }

    @Test
    public void execute_Should_AddNewCategoryToRepository_When_ValidParameters() {
        int originalSize = productRepository.getCategories().size();
        comm.execute(List.of("Etienne"));
        Assertions.assertEquals(originalSize+1, productRepository.getCategories().size());
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        Assertions.assertThrows(InvalidUserInputException.class,() -> {
            ProductRepository productRepository1 = new ProductRepositoryImpl();
            CreateCategoryCommand comm = new CreateCategoryCommand(productRepository1);
//            CommandFactoryImpl commandsFactory = new CommandFactoryImpl();
            List<String> words = new ArrayList<>();
            comm.execute(words);
        });
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateCategoryName() {
        Assertions.assertThrows(DuplicateEntityException.class,() -> {
            ProductRepository productRepository1 = new ProductRepositoryImpl();
            productRepository1.createCategory("Vada");
            CreateCategoryCommand comm = new CreateCategoryCommand(productRepository1);
            List<String> words = List.of("Vada");
            comm.execute(words);
        });
    }

}
