package com.example.fastfoodmanagmentbackend.Model;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;

import java.time.LocalDateTime;
import java.util.List;

public class Order {


    private LocalDateTime orderTime;

    private Money total;

    List<Item> items;
}
