package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class UserImpl implements User {

    public static final int USERNAME_LEN_MIN = 2;
    public static final int USERNAME_LEN_MAX = 20;
    private static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]+$";
    private static final String USERNAME_PATTERN_ERR = "Username contains invalid symbols!";
    private static final String USERNAME_LEN_ERR = format(
            "Username must be between %d and %d characters long!",
            USERNAME_LEN_MIN,
            USERNAME_LEN_MAX);

    public static final int PASSWORD_LEN_MIN = 5;
    public static final int PASSWORD_LEN_MAX = 30;
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
    private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
    private static final String PASSWORD_LEN_ERR = format(
            "Password must be between %s and %s characters long!",
            PASSWORD_LEN_MIN,
            PASSWORD_LEN_MAX);

    public static final int LASTNAME_LEN_MIN = 2;
    public static final int LASTNAME_LEN_MAX = 20;
    private static final String LASTNAME_LEN_ERR = format(
            "Lastname must be between %s and %s characters long!",
            LASTNAME_LEN_MIN,
            LASTNAME_LEN_MAX);

    public static final int FIRSTNAME_LEN_MIN = 2;
    public static final int FIRSTNAME_LEN_MAX = 20;
    private static final String FIRSTNAME_LEN_ERR = format(
            "Firstname must be between %s and %s characters long!",
            FIRSTNAME_LEN_MIN,
            FIRSTNAME_LEN_MAX);

    private final static String NOT_AN_VIP_USER_VEHICLES_ADD = "You are not VIP and cannot add more than %d vehicles!";
    private final static String ADMIN_CANNOT_ADD_VEHICLES = "You are an admin and therefore cannot add vehicles!";

    private static final String YOU_ARE_NOT_THE_AUTHOR = "You are not the author of the comment you are trying to remove!";
    private final static String USER_TO_STRING = "Username: %s, FullName: %s %s, Role: %s";
    private final static String NO_VEHICLES_HEADER = "--NO VEHICLES--";
    private final static String USER_HEADER = "--USER %s--";
    private static final int NORMAL_ROLE_VEHICLE_LIMIT = 5;

    @Override
    public String toString() {
        return String.format(USER_TO_STRING,getUsername(),getFirstName(),getLastName(),getRole());
    }

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<Vehicle> vehicles = new ArrayList<>();
    private UserRole userRole;

    public UserImpl(String username, String firstName, String lastName, String password, List<Vehicle> vehicles, UserRole userRole) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        this.vehicles = vehicles;
        setUserRole(userRole);
    }
    public UserImpl(String username, String firstName, String lastName, String password, UserRole userRole) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setUserRole(userRole);
    }
    public void setUsername(String username) {
        if (username == null || username.trim().length() < USERNAME_LEN_MIN || username.trim().length() > USERNAME_LEN_MAX) {
            throw new IllegalArgumentException(USERNAME_LEN_ERR);
        }
        if (!username.matches(USERNAME_REGEX_PATTERN)) {
            throw new IllegalArgumentException(USERNAME_PATTERN_ERR);
        }
        this.username = username.trim();
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() < FIRSTNAME_LEN_MIN || firstName.trim().length() > FIRSTNAME_LEN_MAX) {
            throw new IllegalArgumentException(FIRSTNAME_LEN_ERR);
        }
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() < LASTNAME_LEN_MIN || lastName.trim().length() > LASTNAME_LEN_MAX) {
            throw new IllegalArgumentException(LASTNAME_LEN_ERR);
        }
        this.lastName = lastName.trim();
    }

    public void setPassword(String password) {
        if (password == null || password.length() < PASSWORD_LEN_MIN || password.length() > PASSWORD_LEN_MAX) {
            throw new IllegalArgumentException(PASSWORD_LEN_ERR);
        }
        if (!password.matches(PASSWORD_REGEX_PATTERN)) {
            throw new IllegalArgumentException(PASSWORD_PATTERN_ERR);
        }
        this.password = password;
    }

    public void setUserRole(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("User role cannot be null!");
        }
        this.userRole = userRole;
    }


    @Override
    public void addVehicle(Vehicle vehicle) {
        if (isAdmin()) {
            throw new IllegalArgumentException(ADMIN_CANNOT_ADD_VEHICLES);
        }
        if (this.userRole == UserRole.NORMAL && this.vehicles.size() >= NORMAL_ROLE_VEHICLE_LIMIT) {
            throw new IllegalArgumentException(String.format(NOT_AN_VIP_USER_VEHICLES_ADD, NORMAL_ROLE_VEHICLE_LIMIT));
        }
        this.vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        if (!this.vehicles.contains(vehicle)) {
            throw new IllegalArgumentException("Vehicle not found in user's collection!");
        }
        this.vehicles.remove(vehicle);
    }

    @Override
    public void addComment(Comment comment, Vehicle vehicle) {
        if (!this.vehicles.contains(vehicle)) {
            throw new IllegalArgumentException("Vehicle not found in user's collection!");
        }
        vehicle.addComment(comment);
    }

    @Override
    public void removeComment(Comment comment, Vehicle vehicle) {
        if (!this.vehicles.contains(vehicle)) {
            throw new IllegalArgumentException("Vehicle not found in user's collection!");
        }
        if (!comment.getAuthor().equals(this.username)) {
            throw new IllegalArgumentException(YOU_ARE_NOT_THE_AUTHOR);
        }
        vehicle.removeComment(comment);
    }

    @Override
    public String printVehicles() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("--USER %s--", getUsername())).append(System.lineSeparator());
        
        if (getVehicles().isEmpty()) {
            result.append("--NO VEHICLES--");
            return result.toString();
        }
        
        for (int i = 0; i < getVehicles().size(); i++) {
            Vehicle vehicle = getVehicles().get(i);
            result.append(String.format("%d. %s", i + 1, vehicle.toString()));
            if (i < getVehicles().size() - 1) {
                result.append(System.lineSeparator());
            }
        }
        
        return result.toString();
    }

    @Override
    public boolean isAdmin() {
        return getRole() == UserRole.ADMIN;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(this.vehicles);
    }
    public String vehiclesToString(){
        if(getVehicles().isEmpty()){
            return NO_VEHICLES_HEADER;
        }
        String allVehicles = "";
        for(Vehicle vehicle: vehicles){
            allVehicles += vehicle.toString();
        }
        return allVehicles;
    }

    public UserRole getRole() {
        return userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl user = (UserImpl) o;
        return username.equals(user.username) && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && userRole == user.userRole;
    }
}