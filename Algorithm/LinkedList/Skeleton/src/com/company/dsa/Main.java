package com.company.dsa;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> queue = new ArrayList<>();
        Map<String, Integer> freq = new HashMap<>();
        String command;
        String[] commandWords;

        do {
            command = sc.nextLine();
            commandWords = command.split(" ");

            switch (commandWords[0]) {
                case "Append":
                    queue.add(commandWords[1]);
                    freq.put(commandWords[1], freq.getOrDefault(commandWords[1], 0) + 1);
                    System.out.println("OK");
                    break;

                case "Insert":
                    try {
                        int index = Integer.parseInt(commandWords[1]);
                        if (index < 0 || index > queue.size()) {
                            System.out.println("Error");
                        } else {
                            queue.add(index, commandWords[2]);
                            freq.put(commandWords[2], freq.getOrDefault(commandWords[2], 0) + 1);
                            System.out.println("OK");
                        }
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    break;

                case "Find":
                    System.out.println(freq.getOrDefault(commandWords[1], 0));
                    break;

                case "Examine":
                    int countToExamine = Integer.parseInt(commandWords[1]);
                    if (countToExamine > queue.size()) {
                        System.out.println("Error");
                        break;
                    }
                    List<String> examinees = new ArrayList<>();
                    for (int i = 0; i < countToExamine; i++) {
                        String removed = queue.remove(0); // O(n) shift
                        examinees.add(removed);
                        freq.put(removed, freq.get(removed) - 1);
                        if (freq.get(removed) == 0) {
                            freq.remove(removed);
                        }
                    }
                    for (String examinee : examinees) {
                        System.out.print(examinee + " ");
                    }
                    System.out.println();
                    break;

                default:
                    break;
            }
        } while (!commandWords[0].equalsIgnoreCase("END"));
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inputs = sc.nextLine().split(" ");
        int K = Integer.parseInt(inputs[0]);
        int N = Integer.parseInt(inputs[1]);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(K);

        int count= 0;
        int result = K;

        while(count < N){
            result = queue.poll();
            count++;

            queue.add(result+1);
            queue.add(2*result+2);
            queue.add(result+2);
        }
        System.out.println(result);
    }
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        String[] soldiers = new String[0];
        int n;
        String s;
        while (cont) {
            n = sc.nextInt();
            sc.nextLine();
            s = sc.nextLine();
            soldiers = s.split(" ");
            if (soldiers.length == n){
                cont = false;
            }
        }

        String[] sergeants = new String[soldiers.length];
        int sCounter = 0;
        String[] corporals = new String[soldiers.length];
        int cCounter = 0;
        String[] privates = new String[soldiers.length];
        int pCounter = 0;
        for(int i = 0;i< soldiers.length;i++){
            if (soldiers[i].charAt(0)=='S'){
                sergeants[sCounter] = soldiers[i];
                sCounter++;
            }else if (soldiers[i].charAt(0)=='C'){
                corporals[cCounter] = soldiers[i];
                cCounter++;
            }else if (soldiers[i].charAt(0)=='P'){
                privates[pCounter] = soldiers[i];
                pCounter++;
            }
        }
        String[] newSoldiers = new String[soldiers.length];
        int i = 0;
        int counter = 0;
        while (sergeants[counter] != null){
            newSoldiers[i] = sergeants[counter];
            i++;
            counter++;
        }
        counter = 0;
        while (corporals[counter] != null){
            newSoldiers[i] = corporals[counter];
            i++;
            counter++;
        }
        counter = 0;
        while (privates[counter] != null){
            newSoldiers[i] = privates[counter];
            i++;
            counter++;
        }

        for (String newSoldier : newSoldiers) {
            System.out.printf(newSoldier+' ');

        }

    }
}
