package com.example.fastfoodmanagmentbackend.Model.Exceptions;

public class OrderDoesNotExistException extends RuntimeException{

    public OrderDoesNotExistException() {
        super("Order does not exist exception!");
    }
}
