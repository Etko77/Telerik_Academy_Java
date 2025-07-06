package com.company.oop.cosmetics.examples;

import java.util.logging.Logger;
import java.util.logging.Level;

public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    public User createUser(String email, int age) {
        try {
            validateEmail(email);
            validateAge(age);
            User user = new User(email, age);

            logger.info("Създаден нов потребител: " + email);
            return user;


        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Критична грешка при създаване на потребител", e);
            throw new SystemException("Системна грешка при създаване на потребител", e);
        }
    }
}
