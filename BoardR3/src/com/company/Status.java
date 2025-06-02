package com.company;

public enum Status {
    OPEN,
    TODO,
    INPROGRESS,
    DONE,
    VERIFIED;

    @Override
    public String toString() {
        switch (this) {
            case OPEN:
                return "Open";
            case VERIFIED:
                return "Verified";
            case DONE:
                return "Done";
            case TODO:
                return "To Do";
            case INPROGRESS:
                return "In Progress";
            default:
                return "Unknown";
        }
    }
}
