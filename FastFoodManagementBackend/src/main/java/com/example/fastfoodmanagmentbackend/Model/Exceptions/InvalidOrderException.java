package com.example.fastfoodmanagmentbackend.Model.Exceptions;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(String message) {
        super(message);
    }
}
