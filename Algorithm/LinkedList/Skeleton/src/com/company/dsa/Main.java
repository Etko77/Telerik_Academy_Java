package com.company.dsa;

import java.util.*;
import java.util.List;

public class Main {
    static void printItems(Iterator<Item> iterator) {
        List<String> output = new ArrayList<>();
        int count = 0;
        while (iterator.hasNext() && count < 10) {
            output.add(count, iterator.next().toString());
            // toString already formats correctly
            count++;
        }
        System.out.println("Ok: " + String.join(", ", output));
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Item> itemsByName = new HashMap<>();
        Map<String, TreeSet<Item>> itemsByType = new HashMap<>();
        TreeSet<Item> itemsByPrice = new TreeSet<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;

            if (line.startsWith("add ")) {
                String[] tokens = line.split(" ", 4);
                String name = tokens[1];
                double price = Double.parseDouble(tokens[2]);
                String type = tokens[3];

                if (itemsByName.containsKey(name)) {
                    System.out.println("Error: Item " + name + " already exists");
                } else {
                    Item item = new Item(name, price, type);
                    itemsByName.put(name, item);
                    itemsByPrice.add(item);
                    itemsByType.computeIfAbsent(type, k -> new TreeSet<>()).add(item);
                    System.out.println("Ok: Item " + name + " added successfully");
                }
            } else if (line.startsWith("filter by type ")) {
                String type = line.substring("filter by type ".length());
                if (!itemsByType.containsKey(type)) {
                    System.out.println("Error: Type " + type + " does not exist");
                } else {
                    TreeSet<Item> set = itemsByType.get(type);
                    printItems(set.iterator());
                }
            } else if (line.startsWith("filter by price ")) {
                String[] parts = line.substring("filter by price ".length()).split(" ");
                double from = Double.NEGATIVE_INFINITY, to = Double.POSITIVE_INFINITY;

                if (parts[0].equals("from")) {
                    from = Double.parseDouble(parts[1]);
                    if (parts.length == 4 && parts[2].equals("to")) {
                        to = Double.parseDouble(parts[3]);
                    }
                } else if (parts[0].equals("to")) {
                    to = Double.parseDouble(parts[1]);
                }

                Iterator<Item> it = itemsByPrice.iterator();
                ArrayList<Item> result = new ArrayList<>();
                while (it.hasNext() && result.size() < 10) {
                    Item item = it.next();
                    if (item.price >= from && item.price <= to) {
                        result.add(item);
                    }
                }
                printItems(result.iterator());
            }
        }







//        System.out.print("How many animals will there be: ");
//        int numberOfAnimals = Integer.parseInt(scanner.nextLine());
//        HashMap<String, Integer> animals = new HashMap<>();
//
//        for(int i = 0 ;i< numberOfAnimals;i++){
//            String name = scanner.nextLine();
//            int num = 1;
//
//            if(animals.containsKey(name)){
//                num += animals.get(name);
//                animals.put(name,num);
//            }else{
//                animals.put(name, 1);
//            }
//        }
//        animals
//                .entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByKey())
//                .forEach((entry ->
//                {
//                    if(entry.getValue() % 2 == 0){
//                        System.out.println(entry.getKey()+" "+entry.getValue()+" "+"Yes");
//                    }else{
//                        System.out.println(entry.getKey()+" "+entry.getValue()+" "+"No");
//                    }
//                }));

    }
}
class Item implements Comparable<Item> {
    String name;
    double price;
    String type;

    public Item(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public int compareTo(Item other) {
        int cmp = Double.compare(this.price, other.price);
        if (cmp != 0) return cmp;
        cmp = this.name.compareTo(other.name);
        if (cmp != 0) return cmp;
        return this.type.compareTo(other.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return this.name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("%s(%.2f)", name, price);
    }
}

