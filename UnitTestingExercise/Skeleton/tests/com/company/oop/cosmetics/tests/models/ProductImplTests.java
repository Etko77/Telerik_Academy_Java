package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.CategoryImpl;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.ProductImpl;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductImplTests {
    private Category category;
    private Product product;
    @BeforeEach
    public void initializeObjects(){
        category = new CategoryImpl("Bomba");
        product = new ProductImpl("Shampoo","Gucci",10000.99, GenderType.MEN);
    }
    @Test
    public void constructor_Should_InitializeProduct_When_ArgumentsAreValid() {
        Assertions.assertEquals("Shampoo", product.getName());
        Assertions.assertEquals("Gucci", product.getBrand());
        Assertions.assertEquals(10000.99, product.getPrice());
        Assertions.assertEquals(GenderType.MEN, product.getGender());
    }
    @Test
    public void constructor_Should_ThrowException_When_NameOrBrandAreInvalidAmountsOfCharacters() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl("S", "Gucci", 10000.99, GenderType.MEN));
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl("Saaaaaaaaaaaaaaa", "Gucci", 10000.99, GenderType.MEN));
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl("Shampoo", "G", 10000.99, GenderType.MEN));
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl("Shampoo", "Gucciiiiiiiiii", 10000.99, GenderType.MEN));
    }
    @Test
    public void constructor_Should_ThrowException_When_PriceIsNegative(){
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl("Shampoo", "Gucci", -10000.99, GenderType.MEN));

    }
}
