package com.example.fastfoodmanagmentbackend.Model;

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

    @Embedded
    private Money price;
    @OneToMany
    private List<Item> extras;

    public Item(String name, Money price, List<Item> extras) {
        this.name = name;
        this.extras = extras;
        if (extras.size() == 0) {
            this.price = price;
        } else {
            for (Item e : extras) {
                this.price = price.add(e.price);
            }
        }
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
