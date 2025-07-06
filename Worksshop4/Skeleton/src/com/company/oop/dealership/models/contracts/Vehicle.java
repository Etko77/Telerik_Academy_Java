package com.company.oop.dealership.models.contracts;

import com.company.oop.dealership.models.enums.VehicleType;

import java.util.List;

public interface Vehicle {

    int getWheels();

    VehicleType getType();

    String getMake();

    String getModel();

    void addComment(Comment comment);

    void removeComment(Comment comment);
    List<Comment> getComments();

}
