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



public class CategoryImplTests {
    private Category category;
    private Product product;
    @BeforeEach
    public void initializeObjects(){
        category = new CategoryImpl("Bomba");
        product = new ProductImpl("Shampoo","Gucci",10000.99, GenderType.MEN);

    }
    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        Assertions.assertEquals("Bomba", category.getName());
    }

    @Test
    public void constructor_Should_InitializeProducts_When_ArgumentsAreValid() {
        Assertions.assertEquals(0,category.getProducts().size());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> new CategoryImpl("B"));
    }

    @Test
    public void addProduct_Should_AddProductToList() {
        category.addProduct(product);
        Assertions.assertEquals(1,category.getProducts().size());
        Assertions.assertTrue(category.getProducts().contains(product));
    }

    @Test
    public void removeProduct_Should_RemoveProductFromList_When_ProductExist() {
        category.addProduct(product);
        category.removeProduct(product);
        Assertions.assertEquals(0,category.getProducts().size());
        Assertions.assertFalse(category.getProducts().contains(product));
    }

    @Test
    public void removeProduct_should_notRemoveProductFromList_when_productNotExist() {
        System.out.println(category.getProducts().contains(product));
        category.removeProduct(product);
        Assertions.assertEquals(0,category.getProducts().size());
    }

}
