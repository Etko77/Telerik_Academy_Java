package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Airplane;

public class AirplaneImpl extends VehicleBase implements Airplane {

    private final boolean hasFreeFood;

    public AirplaneImpl(int id, int passengerCapacity, double pricePerKilometer, boolean hasFreeFood) {
        super(id, passengerCapacity, pricePerKilometer);
        this.hasFreeFood = hasFreeFood;
    }

    @Override
    public boolean hasFreeFood() {
        return hasFreeFood;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.AIR;
    }

    @Override
    public String getAsString() {
        return String.format(
                "Airplane ----\n" +
                "%s\n" +
                "Has free food: %s\n",
                super.getAsString(),
                hasFreeFood);
    }

}