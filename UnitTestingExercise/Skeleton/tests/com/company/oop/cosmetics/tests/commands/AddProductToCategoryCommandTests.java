package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddProductToCategoryCommand;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddProductToCategoryCommandTests {
        private ProductRepository productsRepo;
        private AddProductToCategoryCommand command;
        @BeforeEach
        public void initializeObjects(){
            productsRepo = new ProductRepositoryImpl();
            productsRepo.createProduct("Etienne2","BMW",10000.99, GenderType.MEN);
            productsRepo.createCategory("Bandit");
            command = new AddProductToCategoryCommand(productsRepo);
        }
        @Test
        public void execute_Should_AddNewProductToCategory_When_ValidParameters(){

            command.execute(List.of("Bandit","Etienne2"));
            Assertions.assertTrue(productsRepo.findCategoryByName("Bandit")
                    .getProducts()
                    .stream()
                    .map(p -> p.getName())
                    .anyMatch(str -> str.equals("Etienne2")));
        }
    @Test
    public void execute_Should_ThrowInvalidInputException_When_InvalidParameters(){


        Assertions.assertThrows(InvalidUserInputException.class,() -> {
            command.execute(List.of());
        });
    }
}
