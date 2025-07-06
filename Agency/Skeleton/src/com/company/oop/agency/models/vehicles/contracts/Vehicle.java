package com.company.oop.agency.models.vehicles.contracts;

import com.company.oop.agency.models.vehicles.VehicleType;

public interface Vehicle {

    VehicleType getType();

    int getPassengerCapacity();

    double getPricePerKilometer();

}