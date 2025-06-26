package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.commands.ShowCategoryCommand;
import com.company.oop.cosmetics.core.CommandFactoryImpl;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.contracts.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ShowCategoryCommandTests {
    private ShowCategoryCommand comm;
    private ProductRepository productRepository;
    private CommandFactoryImpl commandsFactory;
    @BeforeEach
    public void initializeCommand(){
        productRepository = new ProductRepositoryImpl();
        productRepository.createCategory("Etienne");
        comm = new ShowCategoryCommand(productRepository);
        commandsFactory = new CommandFactoryImpl();
    }
    @Test
    public void execute_Should_ShowCategory_When_ValidParameters(){
        Category category = productRepository.findCategoryByName("Etienne");

        String result = comm.execute(List.of("Etienne"));
        Assertions.assertEquals(category.print(), result);

    }
    @Test
    public void execute_Should_ThrowException_When_InvalidParameters(){
        Assertions.assertThrows(InvalidUserInputException.class, () -> {
            comm.execute(List.of());
        });
    }
}
