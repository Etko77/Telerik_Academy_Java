package com.company.oop.agency.utils;

import com.company.oop.agency.commands.CommandsConstants;
import com.company.oop.agency.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {

    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.getAsString());
        }

        return String.join(CommandsConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }

}
