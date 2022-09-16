package com.example.fastfoodmanagmentbackend.Model.Exceptions;

public class PlaceNameAlreadyInUseException extends RuntimeException {
    public PlaceNameAlreadyInUseException() {
        super("The place name already exists");
    }
}
