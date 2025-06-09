package com.company.oop.agency.models;

import com.company.oop.agency.exceptions.InvalidUserInputException;
import com.company.oop.agency.models.contracts.Journey;
import com.company.oop.agency.models.contracts.Ticket;

public class TicketImpl implements Ticket {

    private static final String COSTS_POSITIVE_MESSAGE = "Value of 'costs' must be a positive number. Actual value: %.2f.";

    private int id;
    private double costs;
    private Journey journey;

    public TicketImpl(int id, Journey journey, double costs) {
        setId(id);
        setCosts(costs);
        setJourney(journey);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Journey getJourney() {
        return journey;
    }

    @Override
    public double getAdministrativeCosts() {
        return costs;
    }

    @Override
    public double calculatePrice() {
        return costs * journey.calculateTravelCosts();
    }

    @Override
    public String getAsString() {
        return String.format(
                "Ticket ----\n" +
                "Destination: %s\n" +
                "Price: %.2f\n",
                journey.getDestination(),
                calculatePrice());
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setJourney(Journey journey) {
        this.journey = journey;
    }

    private void setCosts(double costs) {
        if (costs < 0) {
            throw new InvalidUserInputException(String.format(COSTS_POSITIVE_MESSAGE, costs));
        }
        this.costs = costs;
    }

}