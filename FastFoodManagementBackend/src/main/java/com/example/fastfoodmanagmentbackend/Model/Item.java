package com.example.fastfoodmanagmentbackend.Model;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;

import javax.persistence.Embedded;
import java.util.List;

public class Item {

    private String name;

    @Embedded
    private Money price;

    private List<Item> extras;

}
