package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardItem {

    private static final int TITLE_MIN_LENGTH = 5;
    private static final int TITLE_MAX_LENGTH = 30;
    private static final Status INITIAL_STATUS = Status.OPEN;
    private static final Status FINAL_STATUS = Status.VERIFIED;

    private String title;
    private Status status;
    private LocalDate dueDate;
    private final List<EventLog> history = new ArrayList<>();

    public BoardItem(String title, LocalDate dueDate) {
        this(title, dueDate, INITIAL_STATUS);
    }

    public BoardItem(String title, LocalDate dueDate, Status status) {
        validateDueDate(dueDate);
        validateTitle(title);

        this.title = title;
        this.status = status;
        this.dueDate = dueDate;

        this.logEvent(String.format("Item created: %s", this.viewInfo()));
    }

    public Status getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        validateTitle(title);

        this.logEvent(String.format("Title changed from %s to %s", this.getTitle(), title));

        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        validateDueDate(dueDate);

        this.logEvent(String.format("DueDate changed from %s to %s", this.getDueDate(), dueDate));

        this.dueDate = dueDate;
    }

    private void setStatus(Status status) {
        this.logEvent(String.format("Status changed from %s to %s", this.getStatus(), status));

        this.status = status;
    }

    public void revertStatus() {
        if (this.status != INITIAL_STATUS) {
            setStatus(Status.values()[status.ordinal() - 1]);
        } else {
            this.logEvent(String.format("Can't revert, already at %s", this.getStatus()));
        }
    }

    public void advanceStatus() {
        if (this.status != FINAL_STATUS) {
            setStatus(Status.values()[status.ordinal() + 1]);
        } else {
            this.logEvent(String.format("Can't advance, already at %s", this.getStatus()));
        }
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", this.title, this.status, this.dueDate);
    }

//    public void displayHistory() {
//        for (EventLog log : history) {
//            System.out.println(log.viewInfo());
//        }
//    }
    public String getHistory(){
        StringBuilder builder = new StringBuilder();

        for(EventLog event: history){
            builder.append(event.viewInfo()).append(System.lineSeparator());
        }

        return builder.toString();
    }


    protected void logEvent(String event) {
        this.history.add(new EventLog(event));
    }

    private void validateTitle(String title) {
        if (title.length() < TITLE_MIN_LENGTH || title.length() > TITLE_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(
                    "Please provide a title with length between %d and %d chars",
                    TITLE_MIN_LENGTH, TITLE_MAX_LENGTH));
        }
    }

    private void validateDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("DueDate can't be in the past");
        }
    }

}
