package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class ShowUsersCommand extends BaseCommand{
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showAllUsers();
    }

    private String showAllUsers() {
        if (!getVehicleDealershipRepository().hasLoggedInUser()) {
            throw new IllegalArgumentException("No user logged in");
        }
        if (getVehicleDealershipRepository().getLoggedInUser().getRole() != UserRole.ADMIN) {
            throw new IllegalArgumentException("You are not an admin!");
        }
        List<User> users = getVehicleDealershipRepository().getUsers();
        StringBuilder usersString = new StringBuilder();
        usersString.append("--USERS--").append(System.lineSeparator());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            usersString.append(String.format("%d. %s", i + 1, user.toString())).append(System.lineSeparator());
        }
        return usersString.toString().trim();
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

}