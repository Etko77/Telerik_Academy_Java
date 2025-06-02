package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        Task task = new Task("Test the application flow", "Pesho", tomorrow);
        Issue issue = new Issue("App flow tests?", "We need to test the App!", tomorrow);

        Board board = new Board();


        board.addItem(task);
        board.addItem(issue);
        ConsoleLogger logger = new ConsoleLogger();
        board.displayHistory(logger);

        System.out.println(task.viewInfo());
        System.out.println(issue.viewInfo());
    }

}
