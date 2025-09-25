package com.company.web.springdemo.exceptions;

public class DuplicateNotFoundException extends RuntimeException {
    public DuplicateNotFoundException(String message) {
        super(message);
    }
}
