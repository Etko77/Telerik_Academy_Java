package com.company;

import java.util.ArrayList;
import java.util.List;

public class Board{

    private final List<BoardItem> items;

    public Board() {
        items = new ArrayList<>();
    }
    ConsoleLogger logger = new ConsoleLogger();

    public void addItem(BoardItem item) {
        if (items.contains(item)) {
            throw new IllegalArgumentException("Item already in the list");
        }

        items.add(item);
    }
    public void displayHistory(){
        for(BoardItem item: items){
            logger.log(item.getHistory());
        }
    }

    public int totalItems() {
        return items.size();
    }


}
