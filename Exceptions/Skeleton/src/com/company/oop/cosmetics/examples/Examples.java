package com.company.oop.cosmetics.examples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Examples {
    //bad file reading practice
    public void readFile(String filename) {
        FileReader file = new FileReader(filename); // Could throw FileNotFoundException
    }
    //error handled practice
    public void readFileWithErrorHandling(String filename) {
        try {
            FileReader file = new FileReader(filename);
            System.out.println("File was found successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file with name   " + filename);
        }
    }
    public void checkedExceptionExample() {
        try {
            // FileNotFoundException е checked exception
            FileReader file = new FileReader("test.txt");

            // IOException е checked exception
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Error reading the file!");
        }
    }
    public void uncheckedExceptionExample() {
        try {
            int[] array = {1, 2, 3};
            System.out.println(array[5]); // ArrayIndexOutOfBoundsException

            String text = null;
            System.out.println(text.length()); // NullPointerException

            int result = 10 / 0; // ArithmeticException

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        } catch (NullPointerException e) {
            System.out.println("Cannot work with null value!");
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by 0!");
        }
        try {
            // Код, който може да хвърли грешка
            riskyOperation();
        } catch (SpecificException e) {
            // Обработка на конкретен тип грешка
            handleSpecificError(e);
        } catch (GeneralException e) {
            // Обработка на общ тип грешка
            handleGeneralError(e);
        }
        FileReader file = null;
        try {
            file = new FileReader("data.txt");
            // Работа с файла
        } catch (FileNotFoundException e) {
            System.out.println("Файлът не е намерен!");
        } finally {
            // Този код се изпълнява винаги
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    System.out.println("Грешка при затваряне на файла!");
                }
            }
        }

    }
    public void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Възрастта не може да бъде отрицателна!");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Възрастта не може да бъде над 150 години!");
        }
        System.out.println("Възрастта е валидна: " + age);
    }
    public void testValidation() {
        try {
            validateAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Грешка при валидация: " + e.getMessage());
        }
    }







}
