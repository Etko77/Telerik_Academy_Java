package com.company.oop.cosmetics.tests.core;

import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.CategoryImpl;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.ProductImpl;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImplTests {
    private ProductRepositoryImpl productRepository;
    private Category category;
    private Product product;
    @BeforeEach
    public void initilaizeRepository(){
        productRepository = new ProductRepositoryImpl();
        category = new CategoryImpl("Shampoos");
        product = new ProductImpl("Shampoo","Gucci",10000.99, GenderType.MEN);
        productRepository.createProduct("Shampoo","Gucci",10000.99, GenderType.MEN);
        productRepository.createCategory("Shampoos");
    }
    @Test
    public void constructor_Should_InitializeProducts() {
        ProductRepositoryImpl repo = new ProductRepositoryImpl();
        Assertions.assertTrue(repo.getProducts().isEmpty());
    }

    @Test
    public void constructor_Should_InitializeCategories() {
        ProductRepositoryImpl repo = new ProductRepositoryImpl();
        Assertions.assertTrue(repo.getCategories().isEmpty());

    }

    @Test
    public void getCategories_Should_ReturnCopyOfCollection() {
        List<Category> copyList = productRepository.getCategories();
        int originalCopySize = copyList.size();
        copyList.clear();
        Assertions.assertEquals(originalCopySize,productRepository.getCategories().size());
    }

    @Test
    public void getProducts_Should_ReturnCopyOfCollection() {
        List<Product> copyList = productRepository.getProducts();
        int originalCopySize = copyList.size();
        copyList.clear();
        Assertions.assertEquals(originalCopySize,productRepository.getProducts().size());
    }

    @Test
    public void categoryExists_Should_ReturnTrue_When_CategoryExists() {
        Assertions.assertTrue(productRepository.categoryExist("Shampoos"));
    }

    @Test
    public void categoryExists_Should_ReturnFalse_When_CategoryDoesNotExist() {
        Assertions.assertFalse(productRepository.categoryExist("Blob"));
    }

    @Test
    public void productExists_Should_ReturnTrue_When_ProductExists() {
        Assertions.assertTrue(productRepository.productExist("Shampoo"));
    }

    @Test
    public void productExists_Should_ReturnFalse_When_ProductDoesNotExist() {
        Assertions.assertFalse(productRepository.productExist("Shampoo0"));
    }

    @Test
    public void createProduct_Should_AddToProducts_When_ArgumentsAreValid() {
        int currentSize = productRepository.getProducts().size();
        productRepository.createProduct("Shampoozi","Gucca",1000.99, GenderType.MEN);

        Assertions.assertEquals(currentSize+1, productRepository.getProducts().size());

    }

    @Test
    public void createCategory_Should_AddToCategories_When_ArgumentsAreValid() {
        int currentSize = productRepository.getCategories().size();
        productRepository.createCategory("Shampooz");
        Assertions.assertEquals(currentSize+1, productRepository.getCategories().size());
    }

    @Test
    public void findCategoryByName_Should_ReturnCategory_When_Exists() {
        Assertions.assertEquals(productRepository.getCategories().get(0),
                productRepository.findCategoryByName(category.getName()));
    }

    @Test
    public void findCategoryByName_Should_ThrowException_When_DoesNotExist() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> {
            new ProductRepositoryImpl().findCategoryByName("1");
        });
    }

    @Test
    public void findProductByName_Should_ReturnProduct_When_Exists() {
        Assertions.assertEquals(productRepository.getProducts().get(0),
                productRepository.findProductByName(product.getName()));
    }

    @Test
    public void findProductByName_Should_ThrowException_When_DoesNotExist() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> {new ProductRepositoryImpl().findProductByName("aaa");});
    }

}
