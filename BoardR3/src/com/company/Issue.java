package com.company;

import java.time.LocalDate;

public class Issue extends BoardItem {

    private final String description;

    public Issue(String title, String description, LocalDate dueDate) {
        super(title, dueDate, Status.OPEN);

        if (description.isEmpty()) {
            this.description = "No description";
        } else {
            this.description = description;
        }
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String viewInfo() {
        // get the common info
        String baseInfo = super.viewInfo();

        // add additional info, based on which subclass you are in
        return String.format("Task: %s, Issue: %s", baseInfo, this.getDescription());
    }

}
