package com.company.web.springdemo.exceptions;

public class UnathorizedOperationException extends RuntimeException {
    public UnathorizedOperationException(String message) {
        super(message);
    }
}
