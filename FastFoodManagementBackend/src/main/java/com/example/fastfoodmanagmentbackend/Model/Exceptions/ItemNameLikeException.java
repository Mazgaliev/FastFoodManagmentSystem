package com.example.fastfoodmanagmentbackend.Model.Exceptions;

public class ItemNameLikeException extends RuntimeException{
    public ItemNameLikeException() {
        super("Item with name already exists");
    }
}
