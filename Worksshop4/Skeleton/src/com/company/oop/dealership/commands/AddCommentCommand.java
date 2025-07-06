package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.utils.ParsingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class AddCommentCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    public final static String COMMENT_ADDED_SUCCESSFULLY = "%s added comment successfully!";
    public final static String VEHICLE_DOES_NOT_EXIST = "The vehicle does not exist!";

    public AddCommentCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String content = parameters.get(0);
        String vehicleOwnerUsername = parameters.get(1);
        int vehicleIndex = ParsingHelpers.tryParseInt(parameters.get(2), INVALID_INPUT_MESSAGE) - 1;
        return addComment(content, vehicleIndex, vehicleOwnerUsername);
    }

    private String addComment(String content, int vehicleIndex, String vehicleOwnerUsername) {
        User vehicleOwner = getVehicleDealershipRepository().findUserByUsername(vehicleOwnerUsername);
        
        if (vehicleOwner == null) {
            throw new IllegalArgumentException(String.format("There is no user with username %s!", vehicleOwnerUsername));
        }

        ValidationHelpers.validateIntRange(vehicleIndex, 0, vehicleOwner.getVehicles().size() - 1, VEHICLE_DOES_NOT_EXIST);

        Vehicle vehicle = vehicleOwner.getVehicles().get(vehicleIndex);

        Comment comment = getVehicleDealershipRepository().createComment(content, getVehicleDealershipRepository().getLoggedInUser().getUsername());

        getVehicleDealershipRepository().getLoggedInUser().addComment(comment, vehicle);

        return String.format(COMMENT_ADDED_SUCCESSFULLY, getVehicleDealershipRepository().getLoggedInUser().getUsername());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}