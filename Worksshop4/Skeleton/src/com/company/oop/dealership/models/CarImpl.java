package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.*;
import com.company.oop.dealership.models.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class CarImpl implements Vehicle, Car, Priceable, Commentable {

    public static final int MAKE_NAME_LEN_MIN = 2;
    public static final int MAKE_NAME_LEN_MAX = 15;
    private static final String MAKE_NAME_LEN_ERR = format(
            "Make must be between %s and %s characters long!",
            MAKE_NAME_LEN_MIN,
            MAKE_NAME_LEN_MAX);
    public static final int MODEL_NAME_LEN_MIN = 1;
    public static final int MODEL_NAME_LEN_MAX = 15;
    private static final String MODEL_NAME_LEN_ERR = format(
            "Model must be between %s and %s characters long!",
            MODEL_NAME_LEN_MIN,
            MODEL_NAME_LEN_MAX);
    public static final double PRICE_VAL_MIN = 0;
    public static final double PRICE_VAL_MAX = 1000000;
    private static final String PRICE_VAL_ERR = format(
            "Price must be between %.1f and %.1f!",
            PRICE_VAL_MIN,
            PRICE_VAL_MAX);
    public static final int CAR_SEATS_MIN = 1;
    public static final int CAR_SEATS_MAX = 10;
    private static final String CAR_SEATS_ERR = format(
            "Seats must be between %d and %d!",
            CAR_SEATS_MIN,
            CAR_SEATS_MAX);


    private String make;
    private String model;
    private double price;
    private final int wheels = 4;
    private final VehicleType type = VehicleType.CAR;
    private int seats;
    private List<Comment> comments = new ArrayList<>();

    public void setMake(String make) {
        if (make == null || make.trim().length() < MAKE_NAME_LEN_MIN || make.trim().length() > MAKE_NAME_LEN_MAX) {
            throw new IllegalArgumentException(MAKE_NAME_LEN_ERR);
        }
        this.make = make.trim();
    }

    public void setModel(String model) {
        if (model == null || model.trim().length() < MODEL_NAME_LEN_MIN || model.trim().length() > MODEL_NAME_LEN_MAX) {
            throw new IllegalArgumentException(MODEL_NAME_LEN_ERR);
        }
        this.model = model.trim();
    }

    public void setPrice(double price) {
        if (price < PRICE_VAL_MIN || price > PRICE_VAL_MAX) {
            throw new IllegalArgumentException(PRICE_VAL_ERR);
        }
        this.price = price;
    }

    public void setSeats(int seats) {
        if (seats < CAR_SEATS_MIN || seats > CAR_SEATS_MAX) {
            throw new IllegalArgumentException(CAR_SEATS_ERR);
        }
        this.seats = seats;
    }

    public CarImpl(String make, String model, double price, int seats) {
        setMake(make);
        setModel(model);
        setPrice(price);
        setSeats(seats);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.removeIf(e -> e.getContent().equals(comment.getContent()) &&
                e.getAuthor().equals(comment.getAuthor()));
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int getSeats() {
        return seats;
    }

    public int getWheels() {
        return wheels;
    }

    @Override
    public List<Comment> getComments() {

        return new ArrayList<>(this.comments);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Car:").append(System.lineSeparator());
        result.append("Make: ").append(make).append(System.lineSeparator());
        result.append("Model: ").append(model).append(System.lineSeparator());
        result.append("Wheels: ").append(wheels).append(System.lineSeparator());
        result.append("Price: $").append((int)price).append(System.lineSeparator());
        result.append("Seats: ").append(seats).append(System.lineSeparator());
        
        if (comments.isEmpty()) {
            result.append("--NO COMMENTS--");
        } else {
            result.append("--COMMENTS--").append(System.lineSeparator());
            for (Comment comment : comments) {
                result.append("----------").append(System.lineSeparator());
                result.append(comment.getContent()).append(System.lineSeparator());
                result.append("User: ").append(comment.getAuthor()).append(System.lineSeparator());
                result.append("----------").append(System.lineSeparator());
            }
            result.append("--COMMENTS--");
        }
        
        return result.toString();
    }
}