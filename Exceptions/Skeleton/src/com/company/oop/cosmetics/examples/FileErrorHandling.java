package com.company.oop.cosmetics.examples;

import java.io.*;

public class FileErrorHandling {

    public void readFileContent(String filename) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            int lineNumber = 1;

            System.out.println("Четене на файл: " + filename);
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Грешка: Файлът '" + filename + "' не е намерен!");
            System.err.println("Моля, проверете пътя и името на файла.");

        } catch (IOException e) {
            System.err.println("Грешка при четене на файла: " + e.getMessage());

        } finally {
            // Затваряне на ресурсите
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Файлът е затворен успешно.");
                } catch (IOException e) {
                    System.err.println("Грешка при затваряне на файла: " + e.getMessage());
                }
            }
        }
    }
    public void readFileModern(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файлът не е намерен: " + filename);
        } catch (IOException e) {
            System.err.println("Грешка при четене: " + e.getMessage());
        }
        try {
            FileReader file = new FileReader("data.txt");
            BufferedReader reader = new BufferedReader(file);
            int number = Integer.parseInt(reader.readLine());

        } catch (FileNotFoundException e) {
            // Конкретна обработка за липсващ файл
            System.out.println("Файлът не е намерен. Създавам нов файл...");
            createDefaultFile();

        } catch (NumberFormatException e) {
            // Конкретна обработка за невалиден номер
            System.out.println("Невалиден формат на числото. Използвам стойност по подразбиране.");
            number = 0;

        } catch (IOException e) {
            // Обща обработка за I/O проблеми
            System.out.println("Проблем с четенето на файла: " + e.getMessage());

        } catch (Exception e) {
            // Най-общ случай за непредвидени грешки
            System.out.println("Неочаквана грешка: " + e.getMessage());
        }
        // Файлът се затваря автоматично
    }

}

}

