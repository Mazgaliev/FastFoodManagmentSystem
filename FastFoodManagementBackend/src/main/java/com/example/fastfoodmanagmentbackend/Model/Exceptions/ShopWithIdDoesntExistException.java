package com.example.fastfoodmanagmentbackend.Model.Exceptions;

public class ShopWithIdDoesntExistException extends RuntimeException {
    public ShopWithIdDoesntExistException() {
        super("Place with this id doesnt exist");
    }
}
