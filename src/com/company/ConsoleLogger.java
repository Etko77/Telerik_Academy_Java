package com.company;

public class ConsoleLogger implements Logger{
    @Override
    public void log(String value) {
        System.out.println(value);
    }
}
