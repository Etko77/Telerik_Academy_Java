package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.*;
import com.company.oop.dealership.models.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MotorcycleImpl implements Vehicle, Motorcycle, Priceable, Commentable {

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
    public static final int CATEGORY_LEN_MIN = 3;
    public static final int CATEGORY_LEN_MAX = 10;
    private static final String CATEGORY_LEN_ERR = format(
            "Category must be between %d and %d characters long!",
            CATEGORY_LEN_MIN,
            CATEGORY_LEN_MAX);
    private String make;
    private String model;
    private double price;
    private final int wheels = 2;
    private final VehicleType type = VehicleType.MOTORCYCLE;
    private String category;
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
    public void setCategory(String category){
        if(category == null || category.trim().length() < CATEGORY_LEN_MIN || category.trim().length() > CATEGORY_LEN_MAX){
            throw new IllegalArgumentException(CATEGORY_LEN_ERR);
        }
        this.category = category;
    }

    public MotorcycleImpl(String make, String model, double price, String category) {
        setMake(make);
        setModel(model);
        setPrice(price);
        setCategory(category);
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
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getWheels() {
        return wheels;
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(this.comments);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Motorcycle:").append(System.lineSeparator());
        result.append("Make: ").append(make).append(System.lineSeparator());
        result.append("Model: ").append(model).append(System.lineSeparator());
        result.append("Wheels: ").append(wheels).append(System.lineSeparator());
        result.append("Price: $").append((int)price).append(System.lineSeparator());
        result.append("Category: ").append(category).append(System.lineSeparator());
        
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