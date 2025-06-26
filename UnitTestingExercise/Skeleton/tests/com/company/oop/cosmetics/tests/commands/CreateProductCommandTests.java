package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.commands.CreateProductCommand;
import com.company.oop.cosmetics.core.CommandFactoryImpl;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateProductCommandTests {
    private CreateProductCommand comm;
    private ProductRepository productRepository;
    private CommandFactoryImpl commandsFactory;
    @BeforeEach
    public void initializeCommand(){
        productRepository = new ProductRepositoryImpl();
        comm = new CreateProductCommand(productRepository);
        commandsFactory = new CommandFactoryImpl();
    }

    @Test
    public void execute_Should_AddNewProductToRepository_When_ValidParameters() {
        int originalSize = productRepository.getProducts().size();
        comm.execute(List.of("Etienne","Etienne", "10.99","MEN"));
        Assertions.assertEquals(originalSize+1, productRepository.getProducts().size());
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        Assertions.assertThrows(InvalidUserInputException.class,() -> {
            ProductRepository productRepository1 = new ProductRepositoryImpl();
            CreateProductCommand comm = new CreateProductCommand(productRepository1);
//            CommandFactoryImpl commandsFactory = new CommandFactoryImpl();
            List<String> words = new ArrayList<>();
            comm.execute(words);
        });
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateProductName() {
        Assertions.assertThrows(DuplicateEntityException.class,() -> {
            ProductRepository productRepository1 = new ProductRepositoryImpl();
            productRepository1.createProduct("Vada","Vada",10.99,GenderType.MEN);
            CreateProductCommand comm = new CreateProductCommand(productRepository1);
            List<String> words = List.of("Vada","Vada","10.99","Men");
            comm.execute(words);
        });
    }
    @Test
    public void execute_Should_ThrowException_When_InvalidPriceParse() {
        Assertions.assertThrows(InvalidUserInputException.class,() -> {
            ProductRepository productRepository1 = new ProductRepositoryImpl();
            productRepository1.createProduct("Vada","Vada",10.99,GenderType.MEN);
            CreateProductCommand comm = new CreateProductCommand(productRepository1);
            List<String> words = List.of("Vada","Vada","aaa","Men");
            comm.execute(words);
        });
    }
    @Test
    public void execute_Should_ThrowException_When_InvalidGenderDataProvided() {
        Assertions.assertThrows(InvalidUserInputException.class,() -> {
            ProductRepository productRepository1 = new ProductRepositoryImpl();
            productRepository1.createProduct("Vada","Vada",10.99,GenderType.MEN);
            CreateProductCommand comm = new CreateProductCommand(productRepository1);
            List<String> words = List.of("Vada","Vada","10.99","bob");
            comm.execute(words);
        });
    }
}
