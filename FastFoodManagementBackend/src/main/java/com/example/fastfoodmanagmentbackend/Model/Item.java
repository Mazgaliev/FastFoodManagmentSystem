package com.example.fastfoodmanagmentbackend.Model;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private ItemType type;

    @Embedded
    private Money price;

    public Item(String name, Money price, ItemType type) {
        this.name = name;
        this.price = price;
    }

    public Item() {

    }

    void changeName(String newName) {
        this.name = newName;
    }

    void changePrice(Money money) {
        this.price = money;
    }
}
