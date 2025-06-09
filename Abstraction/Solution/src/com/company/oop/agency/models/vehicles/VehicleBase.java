package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Vehicle;
import com.company.oop.agency.utils.ValidationHelper;

public abstract class VehicleBase implements Vehicle {

    public static final int PASSENGER_MIN_VALUE = 1;
    public static final int PASSENGER_MAX_VALUE = 800;
    private static final String PASSENGER_ERROR_MESSAGE = "A vehicle with less than 1 passengers or more than 800 passengers cannot exist!";
    public static final double PRICE_MIN_VALUE = 0.1;
    public static final double PRICE_MAX_VALUE = 2.5;
    private static final String PRICE_ERROR_MESSAGE = "A vehicle with a price per kilometer lower than $0.10 or higher than $2.50 cannot exist!";

    private int id;
    private int passengerCapacity;
    private double pricePerKilometer;

    public VehicleBase(int id, int passengerCapacity, double pricePerKilometer) {
        setId(id);
        setPassengerCapacity(passengerCapacity);
        setPricePerKilometer(pricePerKilometer);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String getAsString() {
        return String.format("Passenger capacity: %d\n" +
                             "Price per kilometer: %.2f\n" +
                             "Vehicle type: %s",
                passengerCapacity, pricePerKilometer, getType());
    }

    private void setId(int id) {
        this.id = id;
    }

    protected void validatePassengerCapacity(int passengerCapacity) {
        ValidationHelper.validateValueInRange(
                passengerCapacity,
                PASSENGER_MIN_VALUE,
                PASSENGER_MAX_VALUE,
                PASSENGER_ERROR_MESSAGE);
    }

    protected void validatePricePerKilometer(double pricePerKilometer) {
        ValidationHelper.validateValueInRange(
                pricePerKilometer,
                PRICE_MIN_VALUE,
                PRICE_MAX_VALUE,
                PRICE_ERROR_MESSAGE);
    }

    private void setPassengerCapacity(int passengerCapacity) {
        validatePassengerCapacity(passengerCapacity);
        this.passengerCapacity = passengerCapacity;
    }

    private void setPricePerKilometer(double pricePerKilometer) {
        validatePricePerKilometer(pricePerKilometer);
        this.pricePerKilometer = pricePerKilometer;
    }

}