package com.company.oop.dealership.core.contracts;

import com.company.oop.dealership.models.contracts.*;
import com.company.oop.dealership.models.*;
import com.company.oop.dealership.models.enums.UserRole;

import java.util.List;

public interface VehicleDealershipRepository {

    List<User> getUsers();

    User getLoggedInUser();

    void addUser(User userToAdd);

    User findUserByUsername(String username);

    CarImpl createCar(String make, String model, double price, int seats);

    MotorcycleImpl createMotorcycle(String make, String model, double price, String category);

    TruckImpl createTruck(String make, String model, double price, int weightCapacity);

    UserImpl createUser(String username, String firstName, String lastName, String password, UserRole userRole);

    CommentImpl createComment(String content, String author);

    boolean hasLoggedInUser();

    void login(User user);

    void logout();
}
