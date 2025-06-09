package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Vehicle;
import com.company.oop.agency.utils.ValidationHelper;

public class VehicleBase implements Vehicle {
    public static final int PASSENGER_MIN_VALUE = 1;
    public static final int PASSENGER_MAX_VALUE = 800;
    private static final String PASSENGER_ERROR_MESSAGE = "A vehicle with less than 1 passenger or more than 800 passengers cannot exist!";
    private static final String PRICE_ERROR_MESSAGE = "A vehicle with a price per kilometer lower than $0.10 or higher than $2.50 cannot exist!";
    public static final double PRICE_MIN_VALUE = 0.1;
    public static final double PRICE_MAX_VALUE = 2.5;

    private int id;
    private int passengerCapacity;
    private double pricePerKilometer;
    private final VehicleType vehicleType;

    public VehicleBase(int id, int passengerCapacity, double pricePerKilometer, VehicleType vehicleType) {
        this.id = id;
        setPassengerCapacity(passengerCapacity);
        setPricePerKilometer(pricePerKilometer);
        this.vehicleType = vehicleType;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected void validatePassengerCapacity(int passengerCapacity) {
        ValidationHelper.validateValueInRange(
                passengerCapacity,
                PASSENGER_MIN_VALUE,
                PASSENGER_MAX_VALUE,
                PASSENGER_ERROR_MESSAGE
        );
    }

    protected void validatePricePerKilometer(double pricePerKilometer) {
        ValidationHelper.validateValueInRange(
                pricePerKilometer,
                PRICE_MIN_VALUE,
                PRICE_MAX_VALUE,
                PRICE_ERROR_MESSAGE
        );
    }

    public void setPassengerCapacity(int passengerCapacity) {
        validatePassengerCapacity(passengerCapacity);
        this.passengerCapacity = passengerCapacity;
    }

    public void setPricePerKilometer(double pricePerKilometer) {
        validatePricePerKilometer(pricePerKilometer);
        this.pricePerKilometer = pricePerKilometer;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public String getAsString() {
        return String.format(
                "Passenger capacity: %d\n" +
                        "Price per kilometer: %.2f\n" +
                        "Vehicle type: %s",
                passengerCapacity, pricePerKilometer, getType());
    }
}
