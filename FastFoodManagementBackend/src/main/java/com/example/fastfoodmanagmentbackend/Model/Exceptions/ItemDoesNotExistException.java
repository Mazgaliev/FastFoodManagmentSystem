package com.example.fastfoodmanagmentbackend.Model.Exceptions;

public class ItemDoesNotExistException extends RuntimeException{
    public ItemDoesNotExistException() {
        super("item does not exist cannot EDIT");
    }
}
