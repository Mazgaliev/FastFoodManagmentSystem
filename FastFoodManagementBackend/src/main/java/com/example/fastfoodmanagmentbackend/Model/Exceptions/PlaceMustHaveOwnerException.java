package com.example.fastfoodmanagmentbackend.Model.Exceptions;

public class PlaceMustHaveOwnerException extends RuntimeException{
    public PlaceMustHaveOwnerException(String message) {
        super(message);
    }
}
