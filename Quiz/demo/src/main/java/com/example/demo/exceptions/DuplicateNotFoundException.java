package com.example.demo.exceptions;

public class DuplicateNotFoundException extends RuntimeException {
    public DuplicateNotFoundException(String message) {
        super(message);
    }
}
